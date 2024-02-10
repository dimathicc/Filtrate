package com.dmitryshvalev.filmorate.model;

import com.dmitryshvalev.filmorate.annotation.ReleaseDate;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Builder
public class Film {
    private Integer id;
    @NotBlank(message = "Введите название фильма.")
    private String name;
    @NotNull
    @Size(max = 200, message = "Слишком длинное описание.")
    private String description;
    @NotNull
    @ReleaseDate(value = "1895-12-28", message = "Введите дату релиза не ранее 28 декабря 1895 года.")
    private LocalDate releaseDate;
    @Positive(message = "Продолжительность фильма должна быть больше 0.")
    private Integer duration;
    @NotNull
    private Mpa mpa;
    private final LinkedHashSet<Genre> genres = new LinkedHashSet<>();
}
