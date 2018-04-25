package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.domain.binding.entries.RaceEntriesWrapperXMLImport;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.services.api.RaceEntriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

import static org.softuni.mostwanted.parser.ValidationUtil.isValid;
import static org.softuni.mostwanted.utils.Messages.*;

@Controller
public class RaceEntriesController {

    private RaceEntriesService raceEntriesService;
    private Parser xmlParser;

    @Autowired
    public RaceEntriesController(RaceEntriesService raceEntriesService, @Qualifier("XMLParser") Parser xmlParser) {
        this.raceEntriesService = raceEntriesService;
        this.xmlParser = xmlParser;
    }

    public String importDataFromXML(String xmlContext) {
        StringBuilder sb = new StringBuilder();
        try {
            RaceEntriesWrapperXMLImport raceEntriesDtos = xmlParser.read(RaceEntriesWrapperXMLImport.class, xmlContext);
            raceEntriesDtos.getRaceEntries().forEach(raceEntryDto -> {
                if (!isValid(raceEntryDto)) {
                    sb.append(INCORRECT_DATA_ERROR);
                } else if (this.raceEntriesService.create(raceEntryDto)) {
                    Integer lastId = this.raceEntriesService.getLastId();
                    sb.append(String.format(SUCCESS_FORMAT, "RaceEntry", lastId));
                } else {
                    sb.append(DUPLICATE_ERROR);
                }
                sb.append(System.lineSeparator());
            });
            return sb.toString().trim();
        } catch (IOException | JAXBException e) {
            //   e.printStackTrace();
        }
        return sb.toString().trim();
    }

}
