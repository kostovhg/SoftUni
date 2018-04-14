package products_shop.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import products_shop.domain.model.User;
import products_shop.repository.UserRepository;
import products_shop.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void create(User user) {
        this.userRepository.merge(user);
    }

    @Override
    public User findById(long id) {
        return this.userRepository.findUserById(id);
    }
}
