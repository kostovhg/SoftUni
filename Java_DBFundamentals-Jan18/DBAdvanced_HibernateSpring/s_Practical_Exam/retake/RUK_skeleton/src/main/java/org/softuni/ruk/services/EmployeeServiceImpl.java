package org.softuni.ruk.services;

import org.softuni.ruk.model.dto.EmployeeJSONImportDTO;
import org.softuni.ruk.model.dto.exportJson.EmployeeJSONExportDTO;
import org.softuni.ruk.model.entities.Client;
import org.softuni.ruk.model.entities.Employee;
import org.softuni.ruk.parser.interfaces.ModelParser;
import org.softuni.ruk.repositories.EmployeeRepository;
import org.softuni.ruk.services.api.BranchService;
import org.softuni.ruk.services.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.java2d.pipe.SpanShapeRenderer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;


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

    @Override
    public List<EmployeeJSONExportDTO> findByClientsCount() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return this.employeeRepository.findByClientsCount().stream()
                .map(e -> {
                    EmployeeJSONExportDTO dto = new EmployeeJSONExportDTO();
                    dto.setFullName(String.format("%s %s", e.getFirstName(), e.getLastName()));
                    dto.setSallary(e.getSalary());
                    dto.setStartedOn(df.format(e.getStartedOn()));
                    dto.getClients().addAll(e.getClients().stream().map(Client::getFullName).collect(Collectors.toList()));
                    return dto;
                }).collect(Collectors.toList());
    }
}
