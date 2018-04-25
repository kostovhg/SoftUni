package app.retake.controllers;

import app.retake.domain.dto.AnimalAidJSONImportDTO;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.AnimalAidService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Arrays;

import static app.retake.Config.ERROR_INVALID_DATA;
import static app.retake.parser.ValidationUtil.isValid;

@Controller
public class AnimalAidController {

    private final AnimalAidService animalAidService;
    private final Parser jsonParser;
    private final Parser xmlParser;

    public AnimalAidController(
            AnimalAidService animalAidService,
            @Qualifier("JSONParser") Parser jsonParser,
            @Qualifier("XMLParser") Parser xmlParser) {
        this.animalAidService = animalAidService;
        this.jsonParser = jsonParser;
        this.xmlParser = xmlParser;
    }

    public String importDataFromJSON(String jsonContent) {
        StringBuilder sb = new StringBuilder();
        try {
            AnimalAidJSONImportDTO[] animalAids = jsonParser.read(AnimalAidJSONImportDTO[].class, jsonContent);
            Arrays.stream(animalAids).forEach(a -> {
                if(isValid(a)){
                    animalAidService.create(a);
                    sb.append(String.format("Record %s successfully imported.", a.getName()));
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
}
