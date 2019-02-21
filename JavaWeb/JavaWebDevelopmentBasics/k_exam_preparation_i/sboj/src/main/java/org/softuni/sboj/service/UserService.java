package org.softuni.sboj.service;

import org.softuni.sboj.domain.models.service.UserServiceModel;

public interface UserService {

    UserServiceModel getUserById(String id);

    UserServiceModel getUserByUsername(String name);

    UserServiceModel createUser(UserServiceModel userServiceModel);
}
