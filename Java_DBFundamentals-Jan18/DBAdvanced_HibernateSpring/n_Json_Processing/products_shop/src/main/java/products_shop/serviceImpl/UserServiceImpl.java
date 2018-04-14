package products_shop.serviceImpl;

import products_shop.domain.model.User;
import products_shop.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Override
    public void create(User user) {

    }

    @Override
    public User findById(long id) {
        return null;
    }
}
