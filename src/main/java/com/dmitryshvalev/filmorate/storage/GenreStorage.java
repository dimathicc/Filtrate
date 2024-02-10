package com.dmitryshvalev.filmorate.storage;

import com.dmitryshvalev.filmorate.model.Film;
import com.dmitryshvalev.filmorate.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreStorage {
    List<Genre> findAllGenres();

    Optional<Genre> findGenreById(int id);

    void findAllGenresByFilm(List<Film> films);
}