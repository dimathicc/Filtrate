package com.dmitryshvalev.filmorate.model;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Film {
    private int id;
    @NotNull
    private String name;
    @Max(value = 200)
    private String description;
    private Date releaseDate;
    @Min(value = 1)
    private LocalDateTime duration;
}
