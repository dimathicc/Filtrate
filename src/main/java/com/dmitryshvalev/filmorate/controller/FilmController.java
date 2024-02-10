package com.dmitryshvalev.filmorate.controller;

import com.dmitryshvalev.filmorate.model.Film;
import com.dmitryshvalev.filmorate.service.FilmService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Validated
@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {

    private final FilmService filmsService;

    public FilmController(FilmService filmsService) {
        this.filmsService = filmsService;
    }


    @PostMapping
    public Film create(@Valid @RequestBody Film film) {
        return filmsService.create(film);
    }

    @GetMapping
    public List<Film> findAll() {
        return filmsService.findAllFilms();
    }

    @PutMapping
    public Film update(@Valid @RequestBody Film film) {
        return filmsService.update(film);
    }

    @GetMapping("/{id}")
    public Film findFilmById(@PathVariable("id") int id) {
        return filmsService.findFilmById(id);
    }

    @PutMapping("/{id}/like/{userId}")
    public void addLike(@PathVariable("id") int id, @PathVariable("userId") int userId) {
        filmsService.addLike(id, userId);
    }

    @DeleteMapping("/{id}/like/{userId}")
    public void removeFilmLike(@PathVariable("id") Integer id, @PathVariable("userId") Integer userId) {
        filmsService.removeLike(id, userId);
    }

    @GetMapping("/popular")
    public List<Film> findPopular(@RequestParam(defaultValue = "10") @Positive int count) {
        return filmsService.findPopular(count);
    }

}
