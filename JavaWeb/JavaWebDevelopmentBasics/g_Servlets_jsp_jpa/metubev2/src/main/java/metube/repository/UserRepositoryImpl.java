package metube.repository;

import metube.domain.entities.User;
import metube.domain.entities.User;

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

//    private EntityManager entityManager;
//    private CriteriaBuilder criteriaBuilder;

    public UserRepositoryImpl() {
//        this.entityManager = Persistence
//                .createEntityManagerFactory("metube")
//                .createEntityManager();
//        this.criteriaBuilder = this.entityManager.getCriteriaBuilder();
        super(User.class);
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

//    @Override
//    public long size() {
//        // Corresponds to "SELECT count(t) FROM User t"
//        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
//        Root<User> longRoot = criteriaQuery.from(User.class);
//        criteriaQuery.select(criteriaBuilder.count(longRoot));
//        return entityManager.createQuery(criteriaQuery).getSingleResult();
//    }

//    private Optional<User> findBy(String parameter, String value) {
//
//        try {
//            // Factory for all query components
////            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//            // Criteria object from type expected to be returned
//            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
//            // Object specify source entity
//            Root<User> root = criteriaQuery.from(User.class);
//            // Set query
//            criteriaQuery.where(criteriaBuilder.equal(root.get(parameter), value));
//            // retrieving a query as
//            // "SELECT t FROM table t WHERE parameter = value"
//            TypedQuery<User> q = entityManager.createQuery(criteriaQuery);
//
//            return Optional.of(q.getSingleResult());
//        } catch (NoResultException nre) {
//            return Optional.empty();
//        }
//    }
}
