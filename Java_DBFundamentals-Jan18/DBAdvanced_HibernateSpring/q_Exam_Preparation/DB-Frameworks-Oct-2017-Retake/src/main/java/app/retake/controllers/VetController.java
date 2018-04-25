package app.retake.controllers;

import app.retake.domain.dto.VetWrapperXMLImportDTO;
import app.retake.domain.dto.VetXMLImportDTO;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static app.retake.Config.ERROR_INVALID_DATA;
import static app.retake.parser.ValidationUtil.isValid;

@Controller
public class VetController {

    private final VetService vetService;
    private final Parser xmlParser;

    @Autowired
    public VetController(
            VetService vetService,
            @Qualifier("XMLParser") Parser xmlParser) {
        this.vetService = vetService;
        this.xmlParser = xmlParser;
    }

    public String importDataFromXML(String xmlContent){

        StringBuilder sb = new StringBuilder();
        try {
            VetWrapperXMLImportDTO vetDtoWrapper = xmlParser.read(VetWrapperXMLImportDTO.class, xmlContent);
            vetDtoWrapper.getVets().forEach(v -> {
                if(isValid(v)){
                    vetService.create(v);
                    sb.append(String.format("Record %s successfully imported.", v.getName()));
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
