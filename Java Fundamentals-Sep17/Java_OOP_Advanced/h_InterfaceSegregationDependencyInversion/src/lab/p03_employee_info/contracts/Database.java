package lab.p03_employee_info.contracts;

import lab.p03_employee_info.models.Employee;

import java.util.List;

public interface Database {

    List<Employee> readEmployees();
}
