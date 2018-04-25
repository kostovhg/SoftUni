package app.retake;

import app.retake.controllers.AnimalAidController;
import app.retake.controllers.AnimalController;
import app.retake.controllers.ProcedureController;
import app.retake.controllers.VetController;
import app.retake.io.api.ConsoleIO;
import app.retake.io.api.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static app.retake.Config.*;

@Component
public class Terminal implements CommandLineRunner {

    private final AnimalAidController animalAidController;
    private final AnimalController animalController;
    private final ProcedureController procedureController;
    private final VetController vetController;
    private final ConsoleIO consoleIO;
    private final FileIO fileIO;

    @Autowired
    public Terminal(
            AnimalAidController animalAidController,
            AnimalController animalController,
            ProcedureController procedureController,
            VetController vetController,
            ConsoleIO consoleIO,
            FileIO fileIO) {
        this.animalAidController = animalAidController;
        this.animalController = animalController;
        this.procedureController = procedureController;
        this.vetController = vetController;
        this.consoleIO = consoleIO;
        this.fileIO = fileIO;
    }

    @Override
    public void run(String... strings) throws Exception {

        //
        this.consoleIO.write(this.animalAidController
                .importDataFromJSON(
                        this.fileIO.read(Config.ANIMAL_AIDS_IMPORT_JSON)));

        this.consoleIO.write(this.animalController
                .importDataFromJSON(
                        this.fileIO.read(ANIMALS_IMPORT_JSON)));

        this.consoleIO.write(this.vetController
                .importDataFromXML(
                        this.fileIO.read(VETS_IMPORT_XML)));

        this.consoleIO.write(this.procedureController
                .importDataFromXML(
                        this.fileIO.read(PROCEDURES_IMPORT_XML)));



        System.out.println("test");
    }
}
