package app.retake.controllers;

import app.retake.domain.dto.ProcedureWrapperXMLImportDTO;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.ParseException;

import static app.retake.Config.ERROR_INVALID_DATA;
import static app.retake.parser.ValidationUtil.isValid;

@Controller
public class ProcedureController {

    private ProcedureService procedureService;
    private Parser parser;

    @Autowired
    public ProcedureController(ProcedureService procedureService, @Qualifier("XMLParser") Parser parser) {
        this.procedureService = procedureService;
        this.parser = parser;
    }

    public String importDataFromXML(String xmlContent){
        StringBuilder sb = new StringBuilder();
        try {
            ProcedureWrapperXMLImportDTO procedures = parser.read(ProcedureWrapperXMLImportDTO.class, xmlContent);
            procedures.getProcedures().forEach(p -> {
                if(isValid(p)){
                    try {
                        procedureService.create(p);
                    } catch (ParseException ingored) {
                        //
                    }
                    sb.append("Record successfully imported.");
                } else {
                    sb.append(ERROR_INVALID_DATA);
                }
                sb.append(System.lineSeparator());
            });
            return sb.toString().trim();
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String exportProcedures() throws IOException, JAXBException {
        return null;
    }
}
