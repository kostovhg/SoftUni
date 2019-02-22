package casebook.service;

import casebook.domain.models.service.UserServiceModel;
import casebook.domain.models.view.UserViewModel;

import java.util.List;

public interface UserService {

    boolean registerUser(UserServiceModel map);

    UserServiceModel findByName(String username);

    UserServiceModel loginUser(UserServiceModel userServiceModel);

    List<UserServiceModel> findAllUnknownUsers(String id);

    UserServiceModel getUserById(String id);

    List<UserServiceModel> getAllUsers();

    boolean addFriend(String currentUserId, String id);

    boolean addFriends(UserServiceModel loggedInUser);

    List<UserServiceModel> getUserFriends(String userId);
}
