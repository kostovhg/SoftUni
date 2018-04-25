package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.domain.binding.races.RaceXMLWrapperDto;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.services.api.RacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import static org.softuni.mostwanted.parser.ValidationUtil.isValid;
import static org.softuni.mostwanted.utils.Messages.*;

@Controller
public class RacesController {

    private RacesService racesService;
    private Parser xmlParser;

    @Autowired
    public RacesController(RacesService racesService, @Qualifier("XMLParser") Parser xmlParser) {
        this.racesService = racesService;
        this.xmlParser = xmlParser;
    }

    public String importDataFromXML(String xmlContext) {
        StringBuilder sb = new StringBuilder();
        try{
            RaceXMLWrapperDto racesWrapper = this.xmlParser.read(RaceXMLWrapperDto.class, xmlContext);
            racesWrapper.getRaces().forEach(raceDto -> {
                if(!isValid(raceDto)){
                    sb.append(INCORRECT_DATA_ERROR);
                } else if(this.racesService.create(raceDto)) {
                    Integer lastId = this.racesService.getLastId();
                    sb.append(String.format(SUCCESS_FORMAT, "Race", lastId));
                } else {
                    sb.append(DUPLICATE_ERROR);
                }
                sb.append(System.lineSeparator());
            });
        }catch (Exception e){
            System.out.println(e.getMessage());
            // log
        }
        return sb.toString().trim();
    }

}
