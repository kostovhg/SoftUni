package metube.repository;

import metube.domain.entities.User;
import metube.domain.entities.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl extends BaseRepository<User> implements UserRepository {

    @Inject
    public UserRepositoryImpl(EntityManager entityManager) {
        super(entityManager, User.class);
    }
    
    @Override
    public User save(User entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();

        return entity;
    }

    @Override
    public List<User> findAll() {
        // Same as "SELECT t FROM User t"
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        query.from(User.class);

        return entityManager.createQuery(query)
                .getResultList();
    }

    @Override
    public Optional<User> findById(String s) {
        return findBy("id", s);
    }

    @Override
    public Optional<User> findByName(String name) {
        return this.findBy("username", name);
    }
}
