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

import static metube.utils.Constants.TUBE_EF_ID;
import static metube.utils.Constants.TUBE_EF_NAME;

public class TubeRepositoryImpl implements TubeRepository {

    private final EntityManager entityManager;

    public TubeRepositoryImpl() {
        this.entityManager = Persistence
        .createEntityManagerFactory("metube")
        .createEntityManager();
    }

    @Override
    public Tube save(Tube tube) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(tube);
        this.entityManager.getTransaction().commit();
        return tube;
    }

    @Override
    public List<Tube> findAll() {
        CriteriaQuery<Tube> query = entityManager.getCriteriaBuilder().createQuery(Tube.class);
        query.from(Tube.class);

        return entityManager.createQuery(query)
                .getResultList();
    }

    @Override
    public Optional<Tube> findById(String s) {

        return findBy(TUBE_EF_ID, s);
    }

    @Override
    public Optional<Tube> getByName(String name) {
        return findBy(TUBE_EF_NAME, name);
    }

    private Optional<Tube> findBy(String parameter, String value){

        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Tube> criteriaQuery = criteriaBuilder.createQuery(Tube.class);
            Root<Tube> root = criteriaQuery.from(Tube.class);
            criteriaQuery.where(criteriaBuilder.equal(root.get(parameter), value));
            TypedQuery<Tube> q = entityManager.createQuery(criteriaQuery);

            return Optional.of(q.getSingleResult());
        } catch (NoResultException nre) {
            return Optional.empty();
        }
    }
}
