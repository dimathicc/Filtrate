package com.dmitryshvalev.filmorate.service;

import com.dmitryshvalev.filmorate.model.Film;
import com.dmitryshvalev.filmorate.storage.film.FilmStorage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FilmService {

    private final FilmStorage filmStorage;

    public FilmService(FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
    }

    public void like(int userId, int filmId) {
        filmStorage.like(userId, filmId);
    }

    public void dislike(int id, int postId) {
        filmStorage.dislike(id, postId);
    }

    public Map<Integer, Film> findAll() {
        return filmStorage.findAll();
    }

    public Film add(Film film) {
        return filmStorage.add(film);
    }

    public Film delete(int id) {
        return filmStorage.delete(id);
    }

    public Film update(Film film) {
        return filmStorage.update(film);
    }

    public List<Film> popular(int count) {
        return filmStorage.popular(count);
    }
}
