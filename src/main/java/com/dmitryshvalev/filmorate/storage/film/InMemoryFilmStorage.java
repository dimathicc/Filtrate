package com.dmitryshvalev.filmorate.storage.film;

import com.dmitryshvalev.filmorate.model.Film;
import com.dmitryshvalev.filmorate.storage.user.UserStorage;
import com.dmitryshvalev.filmorate.util.ValidationException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class InMemoryFilmStorage implements FilmStorage {

    private final UserStorage userStorage;

    private final Map<Integer, Film> films = new HashMap<>();
    private int id = 1;

    public InMemoryFilmStorage(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @Override
    public Map<Integer, Film> findAll() {
        return films;
    }

    @Override
    public Film add(Film film) {
        validateFilm(film);
        films.put(id, film);
        id++;
        return film;
    }

    @Override
    public Film delete(int id) {
        return films.remove(id);
    }

    @Override
    public Film update(Film film) {
        validateFilm(film);
        Film updatedFilm = films.get(film.getId());
        updatedFilm.setName(film.getName());
        updatedFilm.setDescription(film.getDescription());
        updatedFilm.setDuration(film.getDuration());
        updatedFilm.setReleaseDate(film.getReleaseDate());
        films.put(film.getId(), updatedFilm);
        return updatedFilm;
    }

    public void like(int userId, int filmId) {
        films.get(filmId).getLikes().add(id);
        userStorage.findUserById(userId).getLikes().add(filmId);
    }

    public void dislike(int id, int postId) {
        films.get(postId).getLikes().remove(id);
        userStorage.findUserById(id).getLikes().remove(postId);
    }

    @Override
    public List<Film> popular(int count) {
        List<Film> sortedFilms = new ArrayList<>(films.values());
        sortedFilms.sort(Collections.reverseOrder());
        return sortedFilms.subList(0, count);
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
