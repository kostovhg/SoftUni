package SystemSplit;

import SystemSplit.engines.Engine;
import SystemSplit.core.Manager;
import SystemSplit.io.ConsoleInputReader;
import SystemSplit.io.ConsoleOutputWriter;
import SystemSplit.utilities.InputParser;

/**
 * Created by Hristo Skipernov on 08/05/2017.
 */
public class Main {
    public static void main(String[] args) {
        ConsoleInputReader inputReader = new ConsoleInputReader();
        ConsoleOutputWriter outputWriter = new ConsoleOutputWriter();
        InputParser inputParser = new InputParser();
        Manager manager = new Manager();
        Engine engine = new Engine(inputReader, outputWriter, inputParser, manager);

        engine.run();
    }
}
