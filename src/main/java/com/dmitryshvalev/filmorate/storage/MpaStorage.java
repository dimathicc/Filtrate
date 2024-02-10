package com.dmitryshvalev.filmorate.storage;

import com.dmitryshvalev.filmorate.model.Mpa;

import java.util.List;
import java.util.Optional;

public interface MpaStorage {
    List<Mpa> findAllMpa();

    Optional<Mpa> findMpaById(int id);
}
