package org.softuni.ruk.controllers;

import org.softuni.ruk.model.dto.ClientJSONImportDTO;
import org.softuni.ruk.parser.interfaces.Parser;
import org.softuni.ruk.services.api.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Arrays;

import static org.softuni.ruk.parser.ValidationUtil.isValid;
import static org.softuni.ruk.terminal.Terminal.CLIENT_CONTROLLER;
import static org.softuni.ruk.utils.Messages.INCORRECT_DATA_ERROR;
import static org.softuni.ruk.utils.Messages.SUCCESS_FORMAT;

@Controller(value = CLIENT_CONTROLLER)
public class ClientController {

    private final ClientService clientService;
    private final Parser jsonParser;
    private final Parser xmlParser;

    @Autowired
    public ClientController(
            ClientService clientService,
            @Qualifier("JSONParser") Parser jsonParser,
            @Qualifier("XMLParser") Parser xmlParser) {
        this.clientService = clientService;
        this.jsonParser = jsonParser;
        this.xmlParser = xmlParser;
    }

    public String importDataFromJSON(String jsonContent) {
        StringBuilder sb = new StringBuilder();
        try {
            ClientJSONImportDTO[] clientDtos = jsonParser.read(ClientJSONImportDTO[].class, jsonContent);
            Arrays.stream(clientDtos).forEach(clientDto -> {
                if (isValid(clientDto)) {
                    this.clientService.create(clientDto);
                    sb.append(String.format(SUCCESS_FORMAT,
                            "Client", clientDto.getFullName()));
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

    public String exportDataToXML() {

        try {
            return this.xmlParser.write(this.clientService.findClientWithMostCardsAndLowestId());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}