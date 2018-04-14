package products_shop.repository.impl;

import products_shop.domain.model.User;
import products_shop.repository.UserRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * Created by User on 1.8.2017 г..
 */
@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

    @Autowired
    private EntityManager em;

    @Override
    public User merge(User user) {
        return em.merge(user);
    }
}
