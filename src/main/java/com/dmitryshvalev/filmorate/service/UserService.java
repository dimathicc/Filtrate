package com.dmitryshvalev.filmorate.service;

import com.dmitryshvalev.filmorate.model.User;
import com.dmitryshvalev.filmorate.storage.user.UserStorage;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public class UserService {

    private final UserStorage userStorage;

    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public Map<Integer, User> findAll() {
        return userStorage.findAll();
    }

    public User findUserById(int id) {
        return userStorage.findUserById(id);
    }

    public User add(User user) {
        return userStorage.add(user);
    }

    public User delete(int id) {
        return userStorage.delete(id);
    }

    public User update(User user) {
        return userStorage.update(user);
    }

    public void addFriend(int id, int userId) {
        userStorage.addFriend(id, userId);
    }

    public void removeFromFriends(int id, int friendId) {
        userStorage.removeFromFriends(id, friendId);
    }

    public Set<Integer> showAllFriends(int id) {
        return userStorage.showAllFriends(id);
    }
}
