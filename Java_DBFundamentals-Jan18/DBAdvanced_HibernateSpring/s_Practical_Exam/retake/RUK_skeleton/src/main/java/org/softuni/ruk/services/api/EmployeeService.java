package org.softuni.ruk.services.api;

import org.softuni.ruk.model.dto.EmployeeJSONImportDTO;
import org.softuni.ruk.model.entities.Employee;

public interface EmployeeService {
    boolean create(EmployeeJSONImportDTO employeeDto);

    Employee findByName(String fullName);
}
