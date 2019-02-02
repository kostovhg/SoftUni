package services;

import entities.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User add(User user);
}
