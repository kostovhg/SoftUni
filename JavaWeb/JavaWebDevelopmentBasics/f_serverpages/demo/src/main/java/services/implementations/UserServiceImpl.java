package services.implementations;

import entities.User;
import services.UserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class UserServiceImpl implements UserService {

    private final List<User> users;

    public UserServiceImpl() {
        this.users = new ArrayList<>();
    }

    @Override
    public List<User> getAllUsers() {
        return Collections.unmodifiableList(users);
    }

    @Override
    public User add(User user) {
        this.users.add(user);
        return user;
    }
}
