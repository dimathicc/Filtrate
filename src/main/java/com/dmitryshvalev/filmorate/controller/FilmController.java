package com.dmitryshvalev.filmorate.controller;

import com.dmitryshvalev.filmorate.model.Film;
import com.dmitryshvalev.filmorate.service.FilmService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {

    private final FilmService filmsService;

    public FilmController(FilmService filmsService) {
        this.filmsService = filmsService;
    }


    @GetMapping
    public Map<Integer, Film> findAll() {
        return filmsService.findAll();
    }

    @PostMapping("/add")
    public Film add(@Valid @RequestBody Film film) {
        return filmsService.add(film);
    }

    @PostMapping("/delete/{id}")
    public Film delete(@PathVariable int id) {
        return filmsService.delete(id);
    }

    @PutMapping("/update")
    public Film update(@Valid @RequestBody Film film) {
        return filmsService.update(film);
    }

    @GetMapping("/top")
    public List<Film> top10PopularFilmsByLikes() {
        return filmsService.top10PopularFilmsByLikes();
    }

    @PostMapping("/{postId}/{id}/like")
    public void like(@PathVariable int id, @PathVariable int postId) {
        filmsService.like(id, postId);
    }

    @PostMapping("/{postId}/{id}/dislike")
    public void dislike(@PathVariable int id, @PathVariable int postId) {
        filmsService.dislike(id, postId);
    }

}
