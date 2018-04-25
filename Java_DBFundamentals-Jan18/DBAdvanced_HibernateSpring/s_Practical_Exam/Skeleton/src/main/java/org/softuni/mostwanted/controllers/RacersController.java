package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.domain.binding.racers.RacersJSONImportDto;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.services.api.RacersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

import static org.softuni.mostwanted.parser.ValidationUtil.isValid;
import static org.softuni.mostwanted.utils.Messages.*;

@Controller
public class RacersController {

    private RacersService racersService;
    private Parser jsonParser;
    private Parser xmlParser;

    @Autowired
    public RacersController(RacersService racersService, @Qualifier("JSONParser") Parser jsonParser, @Qualifier("XMLParser") Parser xmlParser) {
        this.racersService = racersService;
        this.jsonParser = jsonParser;
        this.xmlParser = xmlParser;
    }


    public String importDataFromJSON(String jsonContent) {
        StringBuilder sb = new StringBuilder();
        try {
            RacersJSONImportDto[] racersDtos = this.jsonParser.read(RacersJSONImportDto[].class, jsonContent);
            for (RacersJSONImportDto racerDto : racersDtos) {
                if (!isValid(racerDto)) {
                    sb.append(INCORRECT_DATA_ERROR);
                } else if (this.racersService.create(racerDto)) {
                    sb.append(String.format(SUCCESS_FORMAT, "Racer", racerDto.getName()));
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
            return this.jsonParser.write(this.racersService.getRacersWithCar());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String exportDataToXML() {
        try {
            return this.xmlParser.write(this.racersService.getMostWanted());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }
}
