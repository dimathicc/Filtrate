package com.dmitryshvalev.filmorate.controller;

import com.dmitryshvalev.filmorate.model.User;
import com.dmitryshvalev.filmorate.util.ValidationException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final List<User> users = new ArrayList<>();

    @GetMapping
    public List<User> findAll() {
        return users;
    }

    @PostMapping("/add")
    public User add(@Valid @RequestBody User user) {
        validateUser(user);
        users.add(user);
        log.info("User {} was added", user.getLogin());
        return user;
    }

    @PutMapping("/update")
    public User update(@Valid @RequestBody User updatedUser) {
        validateUser(updatedUser);
        users.add(updatedUser.getId(), updatedUser);
        log.info("User {} updated", updatedUser.getLogin());
        return updatedUser;
    }

    public void validateUser(User user) {
        if (user.getEmail().isEmpty() || !user.getEmail().contains("@")) {
            throw new ValidationException("Email validation failed");
        } else if (user.getLogin().isEmpty() || user.getLogin().contains(" ")) {
            throw new ValidationException("Login validation failed");
        } else if (user.getName().isEmpty()) {
            user.setName(user.getLogin());
        } else if (user.getBirthday().isAfter(LocalDate.now()))
            throw new ValidationException("Birthday validation failed");
    }

}
