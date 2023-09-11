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
    List<Film> top10PopularFilmsByLikes();
    void like(int id, int postId);
    void dislike(int id, int postId);
}
