package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.domain.binding.districts.DistrictJSONImportDto;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.services.api.DistrictsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

import static org.softuni.mostwanted.parser.ValidationUtil.isValid;
import static org.softuni.mostwanted.utils.Messages.*;

@Controller
public class DistrictsController {

    private final DistrictsService districtsService;
    private final Parser jsonParser;

    @Autowired
    public DistrictsController(DistrictsService districtsService, @Qualifier("JSONParser") Parser jsonParser) {
        this.districtsService = districtsService;
        this.jsonParser = jsonParser;
    }

    public String importDataFromJSON(String jsonContent) {
        StringBuilder sb = new StringBuilder();
        try {
            DistrictJSONImportDto[] districtDtos = this.jsonParser.read(DistrictJSONImportDto[].class, jsonContent);
            for (DistrictJSONImportDto districtDto : districtDtos) {
                if (!isValid(districtDto)) {
                    sb.append(INCORRECT_DATA_ERROR);
                } else if (this.districtsService.create(districtDto)) {
                    sb.append(String.format(SUCCESS_FORMAT, "District", districtDto.getName()));
                } else {
                    sb.append(DUPLICATE_ERROR);
                }
                sb.append(System.lineSeparator());
            }
        } catch (JAXBException | IOException e1) {
            // e1.printStackTrace();

        }
        return sb.toString().trim();
    }
}
