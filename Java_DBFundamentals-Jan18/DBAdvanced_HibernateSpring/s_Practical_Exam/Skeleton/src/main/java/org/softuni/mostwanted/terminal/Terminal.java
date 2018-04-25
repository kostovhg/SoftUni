package org.softuni.mostwanted.terminal;

import org.softuni.mostwanted.controllers.*;
import org.softuni.mostwanted.io.interfaces.ConsoleIO;
import org.softuni.mostwanted.io.interfaces.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static org.softuni.mostwanted.utils.Config.*;

@Component
@Transactional
public class Terminal implements CommandLineRunner {

    private final TownsController townsController;
    private final DistrictsController districtsController;
    private final RacersController racersController;
    private final CarController carController;
    private final RaceEntriesController raceEntriesController;
    private final RacesController racesController;
    private final ConsoleIO consoleIO;
    private final FileIO fileIO;

    @Autowired
    public Terminal(
            TownsController townsController,
            DistrictsController districtsController, RacersController racersController, CarController carController, ConsoleIO consoleIO,
            FileIO fileIO, RaceEntriesController raceEntriesController, RacesController racesController) {
        this.townsController = townsController;
        this.districtsController = districtsController;
        this.racersController = racersController;
        this.carController = carController;
        this.consoleIO = consoleIO;
        this.fileIO = fileIO;
        this.raceEntriesController = raceEntriesController;
        this.racesController = racesController;
    }

    @Override
    public void run(String... args) throws Exception {

        importFromFiles();

        exportFiles();

        System.out.println("Test");

    }

    private void exportFiles() {
        try {
            String jsonContent = this.townsController.exportDataToJSON();
            this.fileIO.write(jsonContent, TOWNS_OUTPUT_JSON);
            jsonContent = this.racersController.exportDataToJSON();
            this.fileIO.write(jsonContent, RACERS_OUTPUT_JSON);
            String xmlContent = this.racersController.exportDataToXML();
            this.fileIO.write(xmlContent, MOST_WANTED_OUTPUT_JSON);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void importFromFiles() throws IOException {
        this.consoleIO.write(this.townsController
                .importDataFromJSON(
                        this.fileIO.read(TOWNS_IMPORT_JSON)));

        this.consoleIO.write(this.districtsController
                .importDataFromJSON(
                        this.fileIO.read(DISTRICTS_IMPORT_JSON)));

        this.consoleIO.write(this.racersController
                .importDataFromJSON(
                        this.fileIO.read(RACERS_IMPORT_JSON)));

        this.consoleIO.write(this.carController
                .importDataFromJSON(
                        this.fileIO.read(CARS_IMPORT_JSON)));

        this.consoleIO.write(this.raceEntriesController
                .importDataFromXML(
                        this.fileIO.read(RACE_ENTRIES_IMPORT_XML)));

        this.consoleIO.write(this.racesController
                .importDataFromXML(
                        this.fileIO.read(RACES_IMPORT_XML)));

    }
}
