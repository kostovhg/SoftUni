package metube.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public abstract class BaseRepository<E> implements GenericRepository<E, String> {

    final EntityManager entityManager;
    final CriteriaBuilder criteriaBuilder;
    final Class<E> aClass;

    BaseRepository(Class<E> aClass) {
        this.entityManager = Persistence
                .createEntityManagerFactory("metube")
                .createEntityManager();
        this.criteriaBuilder = this.entityManager.getCriteriaBuilder();
        this.aClass = aClass;
    }

    public List<E> findAll() {
        // Same as "SELECT t FROM theClass t"
        CriteriaQuery<E> query = criteriaBuilder.createQuery(aClass);
        query.from(aClass);

        return entityManager.createQuery(query)
                .getResultList();
    }

    @Override
    public long size() {
        // Corresponds to "SELECT count(t) FROM theClass t"
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<E> longRoot = criteriaQuery.from(aClass);
        criteriaQuery.select(criteriaBuilder.count(longRoot));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    /**
     * JPA criteria API method that works with strings parameters
     * @param parameter - parameter from the Entity (id, name, ...)
     * @param value - value of the searched parameter
     * @return Optional E;
     */
    Optional<E> findBy(String parameter, String value) {

        try {
            // Factory for all query components
//            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            // Criteria object from type expected to be returned
            CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(aClass);
            // Object specify source entity
            Root<E> root = criteriaQuery.from(aClass);
            // Set query
            criteriaQuery.where(criteriaBuilder.equal(root.get(parameter), value));
            // retrieving a query as
            // "SELECT t FROM table t WHERE parameter = value"
            TypedQuery<E> q = entityManager.createQuery(criteriaQuery);

            return Optional.of(q.getSingleResult());
        } catch (NoResultException nre) {
            return Optional.empty();
        } finally {
            //this.entityManager.getTransaction().commit();
        }
    }
}
