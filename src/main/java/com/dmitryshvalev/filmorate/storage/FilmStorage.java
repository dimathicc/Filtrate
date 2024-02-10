package com.dmitryshvalev.filmorate.storage;

import com.dmitryshvalev.filmorate.model.Film;

import java.util.List;
import java.util.Optional;


public interface FilmStorage {

    Film create(Film film);
    Film update(Film film);
    List<Film> findAllFilms();
    List<Film> findPopular(int count);
    Optional<Film> findFilmById(int id);
}
