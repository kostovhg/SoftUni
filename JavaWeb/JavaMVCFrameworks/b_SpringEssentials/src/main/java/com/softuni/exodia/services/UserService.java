package com.softuni.exodia.services;

import com.softuni.exodia.domain.models.service.UserServiceModel;

public interface UserService extends GenericService<UserServiceModel> {

    UserServiceModel registerUser(UserServiceModel userServiceModel);

    UserServiceModel loginUser(UserServiceModel userServiceModel);

    UserServiceModel getUserByUsername(String username);
}
