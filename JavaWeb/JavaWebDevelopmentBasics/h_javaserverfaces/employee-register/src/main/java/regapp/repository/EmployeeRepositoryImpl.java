package regapp.repository;

import regapp.domain.entities.Employee;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final EntityManager entityManager;

    @Inject
    public EmployeeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Employee save(Employee entity) {
        this.entityManager.getTransaction().begin();

        this.entityManager.persist(entity);

        this.entityManager.getTransaction().commit();

        return entity;
    }

    @Override
    public List<Employee> findAll() {

        this.entityManager.getTransaction().begin();

        List<Employee> employees = this.entityManager
                .createQuery("SELECT e FROM Employee e", Employee.class)
                .getResultList();

        return employees;
    }

    @Override
    public Employee findById(String id) {
        this.entityManager.getTransaction().begin();

        Employee employee = this.entityManager
                .createQuery("SELECT e FROM Employee e WHERE e.id = :id", Employee.class)
                .setParameter("id", id)
                .getSingleResult();

        this.entityManager.getTransaction().commit();

        return employee;
    }

    @Override
    public void remove(String id) {

        this.entityManager.getTransaction().begin();

        Query query =  this.entityManager.createQuery("DELETE FROM Employee e WHERE e.id = :id")
                .setParameter("id", id);
        query.executeUpdate();

        this.entityManager.getTransaction().commit();

    }
}
