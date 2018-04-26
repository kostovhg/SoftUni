package org.softuni.ruk.services;

import org.softuni.ruk.model.dto.EmployeeJSONImportDTO;
import org.softuni.ruk.model.entities.Employee;
import org.softuni.ruk.parser.interfaces.ModelParser;
import org.softuni.ruk.repositories.EmployeeRepository;
import org.softuni.ruk.services.api.BranchService;
import org.softuni.ruk.services.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private BranchService branchService;
    private ModelParser modelParser;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, BranchService branchService, ModelParser modelParser) {
        this.employeeRepository = employeeRepository;
        this.branchService = branchService;
        this.modelParser = modelParser;
    }

    @Override
    public boolean create(EmployeeJSONImportDTO employeeDto) {
        try {
            // TODO try to replace it with PropertyMap from ModelParser
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Employee employee = new Employee();
            String[] fullName = employeeDto.getFullName().split("\\s");
            employee.setFirstName(fullName[0]);
            employee.setLastName(fullName[1]);
            employee.setSalary(employeeDto.getSalary());
            employee.setStartedOn(df.parse(employeeDto.getStartedOn()));
            employee.setBranch(this.branchService.findByName(employeeDto.getBranchName()));
            this.employeeRepository.save(employee);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Employee findByName(String fullName) {
        String[] fullNameArr = fullName.split("\\s+");
        return this.employeeRepository.findByFirstNameAndLastName(fullNameArr[0], fullNameArr[1]);
    }
}
