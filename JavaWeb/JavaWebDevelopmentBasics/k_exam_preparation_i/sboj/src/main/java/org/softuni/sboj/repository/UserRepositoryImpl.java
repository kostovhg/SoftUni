package org.softuni.sboj.repository;

import org.softuni.sboj.domain.entities.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class UserRepositoryImpl extends BaseRepository implements UserRepository {

    @Inject
    public UserRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public User findByUsername(String name) {
        return this.executeTransaction((em) -> em
                .createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", name)
                .getSingleResult());
    }

    @Override
    public User save(User user) {
        return this.executeTransaction((em) -> {
            em.persist(user);
            return user;
        });
    }

    @Override
    public List<User> findAll() {
        return this.executeTransaction((em) -> em
                .createQuery("SELECT u FROM User u", User.class)
                .getResultList());
    }

    @Override
    public User findById(String id) {
        return this.executeTransaction((em) -> em
                .createQuery("SELECT u FROM User u WHERE u.id = :id", User.class)
                .setParameter("id", id)
                .getSingleResult());
    }
}
