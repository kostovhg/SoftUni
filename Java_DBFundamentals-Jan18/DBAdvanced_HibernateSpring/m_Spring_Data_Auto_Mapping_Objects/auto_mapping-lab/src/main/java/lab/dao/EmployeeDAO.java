package lab.dao;

import lab.models.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public interface EmployeeDAO extends JpaRepository<Employee, Long> {

    Stream<Employee> findAllByAddress_City_Name(String city);

    Stream<Employee> findAllBySalaryLessThan(BigDecimal upperBount);

    Stream<Employee> findByBirthDateBeforeOrderBySalaryDesc(LocalDate birthDate);


}
