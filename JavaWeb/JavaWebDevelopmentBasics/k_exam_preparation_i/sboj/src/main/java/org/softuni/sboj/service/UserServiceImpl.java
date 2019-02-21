package org.softuni.sboj.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.softuni.sboj.domain.entities.User;
import org.softuni.sboj.domain.models.service.UserServiceModel;
import org.softuni.sboj.repository.UserRepository;

import javax.inject.Inject;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Inject
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserServiceModel getUserById(String id) {
        return this.modelMapper.map(this.userRepository.findById(id), UserServiceModel.class);
    }

    @Override
    public UserServiceModel getUserByUsername(String name) {
        return this.modelMapper.map(this.userRepository.findByUsername(name), UserServiceModel.class);
    }

    @Override
    public UserServiceModel createUser(UserServiceModel userServiceModel) {
        // Hashing users password
        userServiceModel.setPassword(DigestUtils.sha256Hex(userServiceModel.getPassword()));
        // Save and return user
        return this.modelMapper.map(
                this.userRepository.save(
                        this.modelMapper.map(
                                userServiceModel, User.class)),
                UserServiceModel.class);
    }
}
