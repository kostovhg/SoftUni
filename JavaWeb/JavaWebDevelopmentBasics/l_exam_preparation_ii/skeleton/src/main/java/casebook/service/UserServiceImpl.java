package casebook.service;

import casebook.domain.entities.User;
import casebook.domain.models.service.UserServiceModel;
import casebook.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Inject
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserServiceModel findByName(String username) {
        return this.modelMapper.map(this.userRepository.findByUsername(username), UserServiceModel.class);
    }

    @Override
    public boolean registerUser(UserServiceModel userServiceModel) {

        // Hashing the password
        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));

        return this.userRepository.save(user) != null;
    }

    @Override
    public UserServiceModel loginUser(UserServiceModel userServiceModel){
        User user = this.userRepository.findByUsername(userServiceModel.getUsername());

        if (user == null || !user.getPassword().equals(DigestUtils.sha256Hex(userServiceModel.getPassword()))){
            return null;
        }

        return this.modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public List<UserServiceModel> findAllUnknownUsers(String id) {

        return this.userRepository.findAllUnknownUsers(id).stream()
                .map(uu -> this.modelMapper.map(uu, UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserServiceModel getUserById(String id){
        return this.modelMapper.map(this.userRepository.findById(id), UserServiceModel.class);
    }

    @Override
    public List<UserServiceModel> getAllUsers() {
        return this.userRepository.findAll().stream()
                .map(uu -> this.modelMapper.map(uu, UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean addFriend(String currentUserId, String id) {
        try {
            this.userRepository.addFriend(currentUserId, id);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean addFriends(UserServiceModel loggedInUser) {
        User user = this.userRepository.update(this.modelMapper.map(loggedInUser, User.class));
        if (user != null){
            return true;
        }
        return false;
    }

    @Override
    public List<UserServiceModel> getUserFriends(String userId) {
        return this.userRepository.getUserFriends(userId).stream()
                .map(f -> this.modelMapper.map(f, UserServiceModel.class))
                .collect(Collectors.toList());
    }

}
