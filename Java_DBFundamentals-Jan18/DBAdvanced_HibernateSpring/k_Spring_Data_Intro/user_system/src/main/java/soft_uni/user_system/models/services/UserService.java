package soft_uni.user_system.models.services;

import soft_uni.user_system.models.entities.User;

import java.util.List;

public interface UserService {

    void saveUserToDatabase(User user);

    List<User> getAllWhereEmailLike(String domain);
}
