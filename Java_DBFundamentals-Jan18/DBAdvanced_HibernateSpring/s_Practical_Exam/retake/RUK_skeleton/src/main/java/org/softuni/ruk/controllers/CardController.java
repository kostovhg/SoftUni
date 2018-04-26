package org.softuni.ruk.controllers;

import org.softuni.ruk.model.dto.CardsWrapperXMLImportDTO;
import org.softuni.ruk.parser.interfaces.Parser;
import org.softuni.ruk.services.api.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

import static org.softuni.ruk.parser.ValidationUtil.isValid;
import static org.softuni.ruk.utils.Messages.INCORRECT_DATA_ERROR;
import static org.softuni.ruk.utils.Messages.SUCCESS_FORMAT;

@Controller(value = "CardController")
public class CardController {

    private final CardService cardService;
    private final Parser xmlParser;

    @Autowired
    public CardController(CardService cardService, @Qualifier("XMLParser") Parser xmlParser) {
        this.cardService = cardService;
        this.xmlParser = xmlParser;
    }

    public String importDataFromXML(String xmlContent) {
        StringBuilder sb = new StringBuilder();
        try {
            CardsWrapperXMLImportDTO xmlCardsWrapper = xmlParser.read(CardsWrapperXMLImportDTO.class, xmlContent);
            xmlCardsWrapper.getCards().forEach(baDto -> {
                if(isValid(baDto)){
                    cardService.create(baDto);
                    sb.append(String.format(SUCCESS_FORMAT, "Card", baDto.getAccountNumber()));
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