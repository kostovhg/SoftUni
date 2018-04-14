package products_shop.service;

import products_shop.domain.model.User;

public interface UserService {

    void create(User user);

    User findById(long id);
}
