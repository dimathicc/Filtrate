package com.dmitryshvalev.filmorate.controller;

import com.dmitryshvalev.filmorate.model.User;
import com.dmitryshvalev.filmorate.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User create(@Valid @RequestBody User user) {
        userService.create(user);
        return user;
    }

    @PutMapping
    public User update(@Valid @RequestBody User user) {
        userService.update(user);
        return user;
    }

    @Cacheable("allUsers")
    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @Cacheable("userById")
    @GetMapping("/{id}")
    public User findUserById(@PathVariable("id") int id) {
        return userService.findUserById(id);
    }

    @PutMapping("/{id}/friends/{friendId}")
    public void addFriend(@PathVariable("id") int id, @PathVariable("friendId") int friendId) {
        userService.addFriend(id, friendId);
    }

    @DeleteMapping("/{id}/friends/{friendId}")
    public void removeFriend(@PathVariable("id") int id, @PathVariable("friendId") int friendId) {
        userService.removeFriend(id, friendId);
    }

    @GetMapping("/{id}/friends")
    public List<User> findAllFriends(@PathVariable("id") int id) {
        return userService.findAllFriends(id);
    }

    @GetMapping("/{id}/friends/common/{otherId}")
    public List<User> findCommonFriends(@PathVariable("id") int id, @PathVariable("otherId") int otherId) {
        return userService.findCommonFriends(id, otherId);
    }
}
