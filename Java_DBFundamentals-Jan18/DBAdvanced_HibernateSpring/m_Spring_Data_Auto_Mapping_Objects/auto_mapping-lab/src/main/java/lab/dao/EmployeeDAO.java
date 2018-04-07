package lab.dao;

import lab.models.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.stream.Stream;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee, Long> {

    Stream<Employee> findAllByAddress_City_Name(String city);

    Stream<Employee> findAllBySalaryLessThan(BigDecimal upperBount);

    Stream<Employee> findByBirthDateBeforeOrderBySalaryDesc(Date birthDate);

}
