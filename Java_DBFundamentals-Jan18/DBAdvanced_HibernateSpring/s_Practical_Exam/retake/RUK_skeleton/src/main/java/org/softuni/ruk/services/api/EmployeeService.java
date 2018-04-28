package org.softuni.ruk.services.api;

import org.softuni.ruk.model.dto.EmployeeJSONImportDTO;
import org.softuni.ruk.model.dto.exportJson.EmployeeJSONExportDTO;
import org.softuni.ruk.model.entities.Employee;

import java.util.List;

public interface EmployeeService {
    boolean create(EmployeeJSONImportDTO employeeDto);

    Employee findByName(String fullName);

    List<EmployeeJSONExportDTO> findByClientsCount();
}
