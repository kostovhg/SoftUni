package pawInc;

import pawInc.contracts.*;
import pawInc.engines.PawIncEngine;
import pawInc.core.AnimalCenterManager;
import pawInc.io.ConsoleInputReader;
import pawInc.io.ConsoleOutputWriter;
import pawInc.utilities.CommandParser;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Reader reader = new ConsoleInputReader();
        Writer writer = new ConsoleOutputWriter();
        Manager center = new AnimalCenterManager();
        Parser parser = new CommandParser();
        Engine engine = new PawIncEngine(reader, writer, parser, center);


        engine.run();

    }
}
