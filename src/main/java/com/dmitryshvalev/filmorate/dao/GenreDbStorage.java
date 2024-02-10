package com.dmitryshvalev.filmorate.dao;

import com.dmitryshvalev.filmorate.model.Film;
import com.dmitryshvalev.filmorate.model.Genre;
import com.dmitryshvalev.filmorate.storage.GenreStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.function.UnaryOperator.identity;

@Repository
@RequiredArgsConstructor
public class GenreDbStorage implements GenreStorage {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Genre> findAllGenres() {
        String sql = "SELECT * FROM genres";
        return jdbcTemplate.query(sql, (rs, rowNum) -> makeGenre(rs));
    }

    @Override
    public Optional<Genre> findGenreById(int id) {
        String sql = "SELECT * FROM genres WHERE genre_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> makeGenre(rs), id).stream().findFirst();
    }

    @Override
    public void findAllGenresByFilm(List<Film> films) {
        final Map<Integer, Film> filmById = films.stream().collect(Collectors.toMap(Film::getId, identity()));
        String sql = "SELECT * FROM GENRES g, film_genres fg WHERE fg.genre_id = g.genre_id AND fg.film_id IN (%s)";
        String inSql = String.join(",", Collections.nCopies(films.size(), "?"));
        jdbcTemplate.query(String.format(sql, inSql),
                filmById.keySet().toArray(),
                (rs, rowNum) -> filmById.get(rs.getInt("film_id")).getGenres().add(makeGenre(rs)));
    }



    private Genre makeGenre(ResultSet rs) throws SQLException {
        int id = rs.getInt("genre_id");
        String name = rs.getString("name");
        return new Genre(id, name);
    }
}
