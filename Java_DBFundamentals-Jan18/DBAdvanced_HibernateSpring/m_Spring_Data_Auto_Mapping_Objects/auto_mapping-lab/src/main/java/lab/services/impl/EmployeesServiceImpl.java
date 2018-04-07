package lab.services.impl;

import lab.dao.EmployeeDAO;
import lab.models.entities.Employee;
import lab.services.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeesServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;

    @Autowired
    public EmployeesServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public void registerEmployee(Employee employee){
        this.employeeDAO.save(employee);
    }

    @Override
    public List<Employee> listAllByBirthDateBeforeOrderBySalaryDesc(Date birthDate){
        return this.employeeDAO.findByBirthDateBeforeOrderBySalaryDesc(birthDate)
                .collect(Collectors.toList());
    }

    @Override
    public void saveAll(List<Employee> employees) {
        this.employeeDAO.saveAll(employees);
    }

    @Override
    public void save(Employee employee) {
        this.employeeDAO.saveAndFlush(employee);
    }

    @Override
    public Long countAll() {
        return this.employeeDAO.count();
    }

    @Override
    public Employee findById(long id) {
        return this.employeeDAO.getOne(id);
    }


}
