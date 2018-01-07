package theExpanse;

import theExpanse.engines.Engine;
import theExpanse.io.ConsoleInputReader;
import theExpanse.io.ConsoleOutputWriter;
import theExpanse.utilities.InputParser;

public class Main {
    public static void main(String[] args) {
        ConsoleInputReader reader = new ConsoleInputReader();
        ConsoleOutputWriter writer = new ConsoleOutputWriter();
        InputParser parser = new InputParser();
        Engine engine = new Engine(reader, writer, parser);

        engine.run();
    }
}
