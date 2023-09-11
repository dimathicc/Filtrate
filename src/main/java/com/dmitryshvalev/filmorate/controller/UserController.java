package com.dmitryshvalev.filmorate.controller;

import com.dmitryshvalev.filmorate.model.User;
import com.dmitryshvalev.filmorate.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public Map<Integer, User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable int id) {
        return userService.findUserById(id);
    }

    @PostMapping("/add")
    public User add(@Valid @RequestBody User user) {
        return userService.add(user);
    }

    @DeleteMapping("/delete/{id}")
    public User delete(@PathVariable int id) {
        return userService.delete(id);
    }

    @PutMapping("/update")
    public User update(@Valid @RequestBody User user) {
        return userService.update(user);
    }

    @PutMapping("/{id}/friends/{userId}")
    public void addFriend(@PathVariable int id, @PathVariable int userId) {
        userService.addFriend(id, userId);
    }

    @DeleteMapping("/{id}/friends/{friendId}")
    public void removeFromFriends(@PathVariable int id, @PathVariable int friendId) {
        userService.removeFromFriends(id, friendId);
    }

    @GetMapping("/{userId}/friends")
    public Set<Integer> showAllFriends(@PathVariable int userId) {
        return userService.showAllFriends(userId);
    }

}
