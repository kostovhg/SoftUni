package metube.repository;

import metube.domain.entities.Tube;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

public class TubeRepositoryImpl extends BaseRepository<Tube> implements TubeRepository {

    @Inject
    public TubeRepositoryImpl(EntityManager entityManager) {
        super(entityManager, Tube.class);
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
}