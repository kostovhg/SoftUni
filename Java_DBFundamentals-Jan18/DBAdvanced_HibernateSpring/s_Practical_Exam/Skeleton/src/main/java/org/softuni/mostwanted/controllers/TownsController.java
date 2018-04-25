package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.domain.binding.towns.TownJSONImportDto;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.services.api.TownsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

import static org.softuni.mostwanted.parser.ValidationUtil.isValid;
import static org.softuni.mostwanted.utils.Messages.*;

@Controller
public class TownsController {

    private TownsService townsService;
    private Parser jsonParser;

    @Autowired
    public TownsController(TownsService townsService, @Qualifier("JSONParser") Parser jsonParser) {
        this.townsService = townsService;
        this.jsonParser = jsonParser;
    }

    public String importDataFromJSON(String jsonContent) {
        StringBuilder sb = new StringBuilder();
        try {
            TownJSONImportDto[] townsDtos = this.jsonParser.read(TownJSONImportDto[].class, jsonContent);
            for (TownJSONImportDto townDto : townsDtos) {
                if (!isValid(townDto)) {
                    sb.append(INCORRECT_DATA_ERROR);
                } else if (this.townsService.create(townDto)) {
                    sb.append(String.format(SUCCESS_FORMAT, "Town", townDto.getName()));
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

    public String exportDataToJSON(){
        try {
            return this.jsonParser.write(this.townsService.getAllByRacersCount());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
