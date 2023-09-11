package com.dmitryshvalev.filmorate.storage.user;

import com.dmitryshvalev.filmorate.model.User;
import com.dmitryshvalev.filmorate.util.ValidationException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class InMemoryUserStorage implements UserStorage {

    private final Map<Integer, User> users = new HashMap<>();
    private int id = 1;

    @Override
    public Map<Integer, User> findAll() {
        return users;
    }

    @Override
    public User findUserById(int id) {
        return users.get(id);
    }

    @Override
    public User add(User user) {
        validateUser(user);
        user.setId(id);
        users.put(id, user);
        id++;
        return user;
    }

    @Override
    public User delete(int id) {
        return users.remove(id);
    }

    @Override
    public User update(User user) {
        validateUser(user);
        User updatedUser = users.get(user.getId());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setLogin(user.getLogin());
        updatedUser.setName(user.getName());
        updatedUser.setBirthday(user.getBirthday());
        users.put(user.getId(), updatedUser);
        return updatedUser;
    }

    public void addFriend(int id, int userId) {
        users.get(id).getFriends().add(userId);
        users.get(userId).getFriends().add(id);
    }

    public void removeFromFriends(int id, int friendId) {
        users.get(id).getFriends().remove(friendId);
        users.get(friendId).getFriends().remove(id);
    }

    public Set<Integer> showAllFriends(int id) {
        return users.get(id).getFriends();
    }

    public void validateUser(User user) {
        if (user.getEmail().isEmpty()) {
            throw new ValidationException("Email validation failed");
        } else if (user.getLogin().isEmpty() || user.getLogin().contains(" ")) {
            throw new ValidationException("Login validation failed");
        } else if (user.getName().isEmpty()) {
            user.setName(user.getLogin());
        } else if (user.getBirthday().isAfter(LocalDate.now()))
            throw new ValidationException("Birthday validation failed");
    }
}
