package lab.p03_employee_info.contracts;

import lab.p03_employee_info.models.Employee;


public interface InfoProvider {

    Iterable<Employee> getEmployeesByName();

    Iterable<Employee> getEmployeesBySalary();
}
