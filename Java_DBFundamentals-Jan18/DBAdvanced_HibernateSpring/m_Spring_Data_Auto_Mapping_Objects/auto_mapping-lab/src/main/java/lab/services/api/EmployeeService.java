package lab.services.api;

import lab.models.entities.Employee;
import jdk.internal.org.objectweb.asm.tree.analysis.SourceValue;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public interface EmployeeService {

    void registerEmployee(Employee employee);

    List<Employee> listAllByBirthDateBeforeOrderBySalaryDesc(LocalDate birthDate);

    void saveAll(List<Employee> employees);

    void save(Employee secondEmployee);

    Long countAll();

    Employee findById(long id);
}
