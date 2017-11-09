package need_for_speed;

import need_for_speed.engines.Engine;
import need_for_speed.core.CarManager;
import need_for_speed.io.ConsoleInputReader;
import need_for_speed.io.ConsoleOutputWriter;
import need_for_speed.utilities.InputParser;

/**
 * Created by Hristo Skipernov on 08/05/2017.
 */
public class Main {
    public static void main(String[] args) {
        ConsoleInputReader inputReader = new ConsoleInputReader();
        ConsoleOutputWriter outputWriter = new ConsoleOutputWriter();
        InputParser inputParser = new InputParser();
        CarManager carManager = new CarManager();
        Engine engine = new Engine(inputReader, outputWriter, inputParser, carManager);

        engine.run();
    }
}
