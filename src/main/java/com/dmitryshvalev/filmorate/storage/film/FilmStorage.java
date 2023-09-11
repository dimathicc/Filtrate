package com.dmitryshvalev.filmorate.storage.film;

import com.dmitryshvalev.filmorate.model.Film;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public interface FilmStorage {

    Map<Integer, Film> findAll();
    Film add(Film film);
    Film delete(int id);
    Film update(Film film);
    List<Film> popular(int count);
    void like(int userId, int filmId);
    void dislike(int id, int postId);
}
