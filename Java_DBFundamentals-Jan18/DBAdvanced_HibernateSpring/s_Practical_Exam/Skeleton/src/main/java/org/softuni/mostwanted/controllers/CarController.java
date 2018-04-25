package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.domain.binding.cars.CarJSONImportDto;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.services.api.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Arrays;

import static org.softuni.mostwanted.parser.ValidationUtil.isValid;
import static org.softuni.mostwanted.utils.Messages.*;

@Controller
public class CarController {

    private final CarsService carsService;
    private final Parser jsonParser;

    @Autowired
    public CarController(CarsService carsService, @Qualifier("JSONParser") Parser jsonParser) {
        this.carsService = carsService;
        this.jsonParser = jsonParser;
    }

    public String importDataFromJSON(String jsonContent) {
        StringBuilder sb = new StringBuilder();
        try {
            CarJSONImportDto[] carDtos = jsonParser.read(CarJSONImportDto[].class, jsonContent);
            Arrays.stream(carDtos).forEach(car -> {
                if (!isValid(car)) {
                    sb.append(INCORRECT_DATA_ERROR);
                } else if (this.carsService.create(car)) {
                    sb.append(String.format(SUCCESS_FORMAT,
                            "Car",
                            String.format("%s %s @ %d",
                                    car.getBrand(),
                                    car.getModel(),
                                    car.getYearOfProduction())));
                } else {
                    sb.append(DUPLICATE_ERROR);
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
