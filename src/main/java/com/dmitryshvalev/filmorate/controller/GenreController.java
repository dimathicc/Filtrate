package com.dmitryshvalev.filmorate.controller;

import com.dmitryshvalev.filmorate.model.Genre;
import com.dmitryshvalev.filmorate.service.FilmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private final FilmService filmService;

    public GenreController(FilmService filmService) {
        this.filmService = filmService;
    }

    @Cacheable("allGenres")
    @GetMapping
    public List<Genre> findAllGenres() {
        return filmService.findAllGenres();
    }

    @GetMapping("/{id}")
    public Genre findGenreById(@PathVariable("id") int id) {
        return filmService.findGenreById(id);
    }
}