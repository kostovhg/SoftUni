package lab.services.api;

import lab.models.entities.Employee;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public interface EmployeeService {

    void registerEmployee(Employee employee);

    List<Employee> listAllByBirthDateBeforeOrderBySalaryDesc(Date birthDate);

    void saveAll(List<Employee> employees);

    void save(Employee secondEmployee);

    Long countAll();

    Employee findById(long id);
}
