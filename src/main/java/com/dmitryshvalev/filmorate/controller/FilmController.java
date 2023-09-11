package com.dmitryshvalev.filmorate.controller;

import com.dmitryshvalev.filmorate.model.Film;
import com.dmitryshvalev.filmorate.util.ValidationException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {

    private final List<Film> films = new ArrayList<>();

    @GetMapping
    public List<Film> findAll() {
        return films;
    }

    @PostMapping("/add")
    public Film add(@Valid @RequestBody Film film) {
        validateFilm(film);
        films.add(film);
        log.info("Film {} was added", film.getName());
        return film;
    }

    @PutMapping("/update")
    public Film update(@Valid @RequestBody Film updatedFilm) {
        validateFilm(updatedFilm);
        films.add(updatedFilm.getId(), updatedFilm);
        log.info("Film {} was updated", updatedFilm.getName());
        return updatedFilm;
    }

    private void validateFilm(Film film) {
        if (film.getName().isEmpty()) {
            throw new ValidationException("Name shouldn't be empty");
        } else if (film.getDescription().length() >= 200) {
            throw new ValidationException("Description shouldn't be longer than 200 letters");
        } else if (film.getReleaseDate().before(new Date("28-12-1895"))) {
            throw new ValidationException("Date should be before 28 Dec 1895");
        }
    }
}
