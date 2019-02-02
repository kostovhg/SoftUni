package services.implementations;

import entities.User;
import services.UserService;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.servlet.annotation.WebServlet;
import java.util.List;

public class UserServiceImpl implements UserService {


    private final EntityManager entityManager;

    public UserServiceImpl() {
        this.entityManager =
                Persistence.createEntityManagerFactory("usersdb")
                .createEntityManager();
    }

    @Override
    public List<User> getAllUsers() {

        // SELECT * FROM users;
        CriteriaQuery<User> query = entityManager.getCriteriaBuilder().createQuery(User.class);

        query.from(User.class);

        return entityManager.createQuery(query)
                .getResultList();
    }

    @Override
    public User add(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        return user;
    }
}
