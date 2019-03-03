package com.softuni.exodia.services;

import com.softuni.exodia.domain.entities.User;
import com.softuni.exodia.domain.models.service.UserServiceModel;
import com.softuni.exodia.repository.UserRepository;
import com.softuni.exodia.utils.MapperUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends BaseService<UserServiceModel> implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(MapperUtil mapper, UserRepository userRepository) {
        super(mapper);
        this.userRepository = userRepository;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {

        userServiceModel.setPassword(DigestUtils.sha256Hex(userServiceModel.getPassword()));

        return this.create(userServiceModel);
    }

    @Override
    public UserServiceModel loginUser(UserServiceModel userServiceModel) {
        return this.userRepository.findByUsername(userServiceModel.getUsername())
                .filter(u -> u.getPassword().equals(DigestUtils.sha256Hex(userServiceModel.getPassword())))
                .map(u -> this.mapper.map(u, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public UserServiceModel create(UserServiceModel createModel) {
        User user = super.mapper.map(createModel, User.class);
        try {
            this.userRepository.saveAndFlush(user);
            return mapper.map(user, UserServiceModel.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserServiceModel findBy(String parameter, String value) {

        switch (parameter) {
            case "username":
                return this.getUserByUsername(value);
        }

        return null;
    }

    @Override
    public UserServiceModel getUserByUsername(String username) {
        return super.mapper.map(this.userRepository.findByUsername(username), UserServiceModel.class);
    }
}
