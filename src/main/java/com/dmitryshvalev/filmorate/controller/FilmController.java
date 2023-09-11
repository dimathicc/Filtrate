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

    @GetMapping("/popular")
    public List<Film> popular(@RequestParam(defaultValue = "10") Integer count) {
        return filmsService.popular(count);
    }

    @PutMapping("/{filmId}/like/{userId}")
    public void like(@PathVariable int userId, @PathVariable int filmId) {
        filmsService.like(userId, filmId);
    }

    @DeleteMapping("/{postId}/like/{id}")
    public void dislike(@PathVariable int id, @PathVariable int postId) {
        filmsService.dislike(id, postId);
    }

}
