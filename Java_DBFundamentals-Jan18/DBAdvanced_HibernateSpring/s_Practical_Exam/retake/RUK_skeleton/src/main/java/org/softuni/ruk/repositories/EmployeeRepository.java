package org.softuni.ruk.repositories;

import org.softuni.ruk.model.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("select e FROM Employee AS e where e.firstName like :firstName AND e.lastName like :lastName")
    Employee findByFirstNameAndLastName(@Param("firstName")String firstName, @Param("lastName")String lastName);
}
