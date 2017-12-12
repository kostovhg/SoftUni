package lab.p03_employee_info.models;

import lab.p03_employee_info.contracts.Database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmployeeDatabase implements Database {

    @Override
    public List<Employee> readEmployees() {
        List<Employee> employees = new ArrayList<>();
        Collections.addAll(employees,
                new Employee("Pesho", 20),
                new Employee("Gosho", 40),
                new Employee("Stamat", 50),
                new Employee("Atanas", 50));

        return employees;
    }
}
