package com.dmitryshvalev.filmorate.controller;

import com.dmitryshvalev.filmorate.model.Mpa;
import com.dmitryshvalev.filmorate.service.FilmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mpa")
public class MpaController {

    private final FilmService filmService;

    public MpaController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public List<Mpa> findAllMpa() {
        return filmService.findAllMpa();
    }

    @GetMapping("/{id}")
    public Mpa findMpaById(@PathVariable("id") int id) {
        return filmService.findMpaById(id);
    }
}