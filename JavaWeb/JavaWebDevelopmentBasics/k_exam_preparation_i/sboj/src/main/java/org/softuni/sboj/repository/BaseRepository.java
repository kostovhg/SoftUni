package org.softuni.sboj.repository;


import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.function.Function;

public abstract class BaseRepository {

    private final EntityManager entityManager;

    @Inject
    protected BaseRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    <T> T executeTransaction(Function<EntityManager, T> func) {

        T result = null;

        try {
            this.entityManager.getTransaction().begin();
            result = func.apply(this.entityManager);
            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            e.printStackTrace();
        }

        return result;
    }
}
