package products_shop.repository;

import products_shop.domain.model.User;

/**
 * Created by User on 1.8.2017 г..
 */
public interface UserRepositoryCustom {

    User merge(User user);
}
