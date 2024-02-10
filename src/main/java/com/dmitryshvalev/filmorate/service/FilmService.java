package com.dmitryshvalev.filmorate.service;

import com.dmitryshvalev.filmorate.exception.FilmNotFoundException;
import com.dmitryshvalev.filmorate.exception.GenreNotFoundException;
import com.dmitryshvalev.filmorate.exception.MpaNotFoundException;
import com.dmitryshvalev.filmorate.exception.UserNotFoundException;
import com.dmitryshvalev.filmorate.model.Film;
import com.dmitryshvalev.filmorate.model.Genre;
import com.dmitryshvalev.filmorate.model.Mpa;
import com.dmitryshvalev.filmorate.storage.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class FilmService {
    private final FilmStorage filmsStorage;
    private final MpaStorage mpaStorage;
    private final GenreStorage genreStorage;
    private final LikeStorage likeStorage;
    private final UserStorage userStorage;

    public Film create(Film film) {
        return filmsStorage.create(film);
    }

    public Film update(Film film) {
        if (filmsStorage.findFilmById(film.getId()).isEmpty()) {
            throw new FilmNotFoundException("Фильм не найден.");
        }
        return filmsStorage.update(film);
    }

    public List<Film> findAllFilms() {
        List<Film> films = filmsStorage.findAllFilms();
        genreStorage.findAllGenresByFilm(films);
        return films;
    }

    public Film findFilmById(int id) {
        Film film = filmsStorage.findFilmById(id).orElseThrow(() -> new FilmNotFoundException("Фильм не найден."));
        genreStorage.findAllGenresByFilm(List.of(film));
        return film;
    }

    public void addLike(int id, int userId) {
        if (userStorage.findUserById(id).isEmpty() || userStorage.findUserById(userId).isEmpty()) {
            throw new UserNotFoundException("Пользователь не найден.");
        }
        likeStorage.addLike(id, userId);
    }

    public void removeLike(int id, int userId) {
        if (userStorage.findUserById(id).isEmpty() || userStorage.findUserById(userId).isEmpty()) {
            throw new UserNotFoundException("Пользователь не найден.");
        }
        likeStorage.removeLike(id, userId);
    }

    public List<Film> findPopular(int count) {
        List<Film> films = filmsStorage.findPopular(count);
        genreStorage.findAllGenresByFilm(films);
        return films;
    }

    public List<Mpa> findAllMpa() {
        return mpaStorage.findAllMpa();
    }

    public Mpa findMpaById(int id) {
        return mpaStorage.findMpaById(id).orElseThrow(() -> new MpaNotFoundException("Рейтинг MPA не найден."));
    }

    public List<Genre> findAllGenres() {
        return genreStorage.findAllGenres();
    }

    public Genre findGenreById(int id) {
        return genreStorage.findGenreById(id).orElseThrow(() -> new GenreNotFoundException("Жанр не найден."));
    }
}
