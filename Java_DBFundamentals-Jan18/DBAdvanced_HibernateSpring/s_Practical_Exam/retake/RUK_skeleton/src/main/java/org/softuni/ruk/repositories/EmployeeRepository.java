package org.softuni.ruk.repositories;

import org.softuni.ruk.model.dto.exportJson.EmployeeJSONExportDTO;
import org.softuni.ruk.model.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("select e FROM Employee AS e where e.firstName like :firstName AND e.lastName like :lastName")
    Employee findByFirstNameAndLastName(@Param("firstName")String firstName, @Param("lastName")String lastName);

    @Query("SELECT e FROM Employee AS e " +
            "WHERE e.clients.size > 0 " +
            "ORDER BY e.clients.size DESC, e.id ASC")
    List<Employee> findByClientsCount();
}
