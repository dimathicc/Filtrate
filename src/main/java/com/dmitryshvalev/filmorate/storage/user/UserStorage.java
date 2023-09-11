package com.dmitryshvalev.filmorate.storage.user;

import com.dmitryshvalev.filmorate.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserStorage {
    Map<Integer, User> findAll();
    User findUserById(int id);
    User add(User user);
    User delete(int id);
    User update(User user);
    void addFriend(int id, int userId);
    void removeFromFriends(int id, int friendId);
    Set<Integer> showAllFriends(int id);
}
