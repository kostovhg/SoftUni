package org.softuni.ruk.controllers;

import org.softuni.ruk.model.dto.BranchJSONImportDTO;
import org.softuni.ruk.parser.interfaces.Parser;
import org.softuni.ruk.services.api.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Arrays;

import static org.softuni.ruk.parser.ValidationUtil.isValid;
import static org.softuni.ruk.utils.Messages.*;

@Controller(value = "BranchController")
public class BranchController {

    private final BranchService branchService;
    private final Parser jsonParser;

    @Autowired
    public BranchController(BranchService branchService, @Qualifier("JSONParser") Parser jsonParser) {
        this.branchService = branchService;
        this.jsonParser = jsonParser;
    }

    public String importDataFromJSON(String jsonContent) {
        StringBuilder sb = new StringBuilder();
        try {
            BranchJSONImportDTO[] branchDtos = jsonParser.read(BranchJSONImportDTO[].class, jsonContent);
            Arrays.stream(branchDtos).forEach(branchDto -> {
                if (isValid(branchDto)) {
                    this.branchService.create(branchDto);
                    sb.append(String.format(SUCCESS_FORMAT,
                            "Branch", branchDto.getName()));
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