package org.softuni.sboj.repository;

import org.softuni.sboj.domain.entities.JobApplication;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class JobApplicationRepositoryImpl extends BaseRepository implements JobApplicationRepository {

    @Inject
    public JobApplicationRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public JobApplication save(JobApplication jobApplication) {
        return this.executeTransaction((em) -> {
            em.persist(jobApplication);
            return jobApplication;
        });
    }

    @Override
    public List<JobApplication> findAll() {
        return this.executeTransaction((em) -> em
                .createQuery("SELECT ja FROM JobApplication ja", JobApplication.class)
                .getResultList());
    }

    @Override
    public JobApplication findById(String id) {
        return this.executeTransaction((em) -> em
                .createQuery("SELECT ja FROM JobApplication ja WHERE ja.id = :id", JobApplication.class)
                .setParameter("id", id)
                .getSingleResult());
    }

    @Override
    public void delete(String id) {
        this.executeTransaction((em) -> {
           em.createQuery("DELETE FROM JobApplication ja WHERE ja.id=:id")
           .setParameter("id", id).executeUpdate();
           return null;
        });
    }
}
