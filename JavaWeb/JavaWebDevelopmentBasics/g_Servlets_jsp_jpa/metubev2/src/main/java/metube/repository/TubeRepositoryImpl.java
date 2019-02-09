package metube.repository;

import metube.domain.entities.Tube;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class TubeRepositoryImpl extends BaseRepository<Tube> implements TubeRepository {

    private EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;

    public TubeRepositoryImpl() {
//        this.entityManager = Persistence
//                .createEntityManagerFactory("metube")
//                .createEntityManager();
//        this.criteriaBuilder = this.entityManager.getCriteriaBuilder();
        super(Tube.class);
    }

    @Override
    public Tube save(Tube entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();

        return entity;
    }

    @Override
    public List<Tube> findAll() {
        // Same as "SELECT t FROM Tube t"
        CriteriaQuery<Tube> query = criteriaBuilder.createQuery(Tube.class);
        query.from(Tube.class);

        return entityManager.createQuery(query)
                .getResultList();
    }

    @Override
    public Optional<Tube> findById(String s) {
        return findBy("id", s);
    }

    @Override
    public Optional<Tube> findByName(String name) {
        return findBy("title", name);
    }

//    public long size() {
//        // Corresponds to "SELECT count(t) FROM Tube t"
//        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
//        Root<Tube> longRoot = criteriaQuery.from(Tube.class);
//        criteriaQuery.select(criteriaBuilder.count(longRoot));
//        return entityManager.createQuery(criteriaQuery).getSingleResult();
//    }

//    /**
//     * JPA criteria API method that works with strings parameters
//     * @param parameter - parameter from the Entity (id, name, ...)
//     * @param value - value of the searched parameter
//     * @return Optional Tube;
//     */
//    private Optional<Tube> findBy(String parameter, String value) {
//
//        try {
//            // Factory for all query components
//            //CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//            // Criteria object from type expected to be returned
//            CriteriaQuery<Tube> criteriaQuery = criteriaBuilder.createQuery(Tube.class);
//            // Object specify source entity
//            Root<Tube> root = criteriaQuery.from(Tube.class);
//            // Set query
//            criteriaQuery.where(criteriaBuilder.equal(root.get(parameter), value));
//            // retrieving a query as
//            // "SELECT t FROM table t WHERE parameter = value"
//            TypedQuery<Tube> q = entityManager.createQuery(criteriaQuery);
//
//            return Optional.of(q.getSingleResult());
//        } catch (NoResultException nre) {
//            return Optional.empty();
//        }
//    }
}
