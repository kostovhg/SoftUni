package metube.service;

import metube.domain.entities.User;
import metube.domain.models.service.UserServiceModel;
import metube.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

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
    public boolean registerUser(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setPassword(DigestUtils.sha256Hex(userServiceModel.getPassword()));

        try {
            this.userRepository.save(user);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean login(UserServiceModel userServiceModel) {

        User user = this.userRepository.findByName(userServiceModel.getUsername()).orElse(null);

        return user != null && user.getPassword().equals(DigestUtils.sha256Hex(userServiceModel.getPassword()));
    }
}
