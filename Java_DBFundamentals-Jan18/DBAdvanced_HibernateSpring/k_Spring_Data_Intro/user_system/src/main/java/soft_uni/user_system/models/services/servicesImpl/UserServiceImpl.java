package soft_uni.user_system.models.services.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soft_uni.user_system.models.entities.User;
import soft_uni.user_system.models.repositories.UserRepository;
import soft_uni.user_system.models.services.UserService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void saveUserToDatabase(User user){
        this.userRepository.save(user);
    }

    @Override
    public List<User> getAllWhereEmailLike(String lookupString) {
        return this.userRepository.findAllByEmailEndsWith(lookupString);
    }

    @Override
    public User getByUsername(String username){
        return this.userRepository.getByUsername(username);
    }

}
