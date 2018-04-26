package org.softuni.ruk.controllers;

import org.softuni.ruk.model.dto.EmployeeJSONImportDTO;
import org.softuni.ruk.parser.interfaces.Parser;
import org.softuni.ruk.services.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Arrays;

import static org.softuni.ruk.parser.ValidationUtil.isValid;
import static org.softuni.ruk.utils.Messages.INCORRECT_DATA_ERROR;
import static org.softuni.ruk.utils.Messages.SUCCESS_FORMAT;

@Controller(value = "EmployeeController")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final Parser jsonParser;

    @Autowired
    public EmployeeController(EmployeeService employeeService, @Qualifier("JSONParser") Parser jsonParser) {
        this.employeeService = employeeService;
        this.jsonParser = jsonParser;
    }

    public String importDataFromJSON(String jsonContent) {
        StringBuilder sb = new StringBuilder();
        try {
            EmployeeJSONImportDTO[] employeeDtos = jsonParser.read(EmployeeJSONImportDTO[].class, jsonContent);
            Arrays.stream(employeeDtos).forEach(employeeDto -> {
                if (isValid(employeeDto)) {
                    this.employeeService.create(employeeDto);
                    sb.append(String.format(SUCCESS_FORMAT,
                            "Employee", employeeDto.getFullName()));
                } else {
                    sb.append(INCORRECT_DATA_ERROR);
                }
                sb.append(System.lineSeparator());
            });
            return sb.toString().trim();
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}