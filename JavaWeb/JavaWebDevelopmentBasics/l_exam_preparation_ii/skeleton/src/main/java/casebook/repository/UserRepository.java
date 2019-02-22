package casebook.repository;

import casebook.domain.entities.User;

import java.util.Arrays;
import java.util.List;

public interface UserRepository extends GenericRepository<User, String> {

    User findByUsername(String username);

    List<User> findAllUnknownUsers(String id);

    void addFriend(String currentUserId, String id);

    List<User> getUserFriends(String userId);
}
