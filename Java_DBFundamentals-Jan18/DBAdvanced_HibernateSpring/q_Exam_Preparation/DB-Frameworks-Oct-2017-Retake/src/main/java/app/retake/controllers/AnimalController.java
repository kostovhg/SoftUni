package app.retake.controllers;

import app.retake.domain.dto.AnimalJSONImportDTO;
import app.retake.parser.ValidationUtil;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

import static app.retake.Config.ERROR_INVALID_DATA;
import static app.retake.parser.ValidationUtil.isValid;

@Controller
public class AnimalController {

    private final AnimalService animalService;
    private final Parser parser;

    @Autowired
    public AnimalController(
            AnimalService animalService,
            @Qualifier("JSONParser") Parser parser) {
        this.animalService = animalService;
        this.parser = parser;
    }

    public String importDataFromJSON(String jsonContent) {
        StringBuilder sb = new StringBuilder();
        try {
            AnimalJSONImportDTO[] animalsDto = parser.read(AnimalJSONImportDTO[].class, jsonContent);
            Arrays.stream(animalsDto).forEach(a -> {
                //Set<String> errors = ValidationUtil.getErrors(a);
                if(isValid(a)){
                    animalService.create(a);
                    sb.append(String.format("Record %s Passport №: %s successfully imported.", a.getName(), a.getPassport().getSerialNumber()));
                } else {
                    sb.append(ERROR_INVALID_DATA);
                }
                sb.append(System.lineSeparator());
            });
            return sb.toString().trim();
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String exportAnimalsByOwnerPhoneNumber(String phoneNumber) {
        return null;
    }
}
