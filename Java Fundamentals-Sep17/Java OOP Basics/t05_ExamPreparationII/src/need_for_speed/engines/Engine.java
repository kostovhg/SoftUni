package need_for_speed.engines;

import need_for_speed.core.CarManager;
import need_for_speed.io.ConsoleInputReader;
import need_for_speed.io.ConsoleOutputWriter;
import need_for_speed.utilities.InputParser;

import java.util.List;

import static need_for_speed.utilities.Constants.INPUT_TERMINATING_COMMAND;

public class Engine {
    private final ConsoleInputReader inputReader;
    private final ConsoleOutputWriter outputWriter;
    private final InputParser inputParser;
    private final CarManager carManager;

    public Engine(ConsoleInputReader inputReader, ConsoleOutputWriter outputWriter, InputParser inputParser, CarManager carManager) {
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
        this.inputParser = inputParser;
        this.carManager = carManager;
    }

    public void run() {
        String inputLine;

        while (true) {
            inputLine = this.inputReader.readLine();
            if (inputLine.equals(INPUT_TERMINATING_COMMAND)) {
                break;
            }
            List<String> commandParams = this.inputParser.parseInput(inputLine);
            String result = this.dispatchCommand(commandParams);
            if (result != null) {
                this.outputWriter.writeLine(result);
            }
        }
    }

    private String dispatchCommand(List<String> commandParams) {
        String command = commandParams.remove(0);

        switch (command) {
            case "register":
                this.carManager.register(
                        Integer.parseInt(commandParams.get(0)), commandParams.get(1),
                        commandParams.get(2), commandParams.get(3),
                        Integer.parseInt(commandParams.get(4)), Integer.parseInt(commandParams.get(5)),
                        Integer.parseInt(commandParams.get(6)), Integer.parseInt(commandParams.get(7)),
                        Integer.parseInt(commandParams.get(8)));
                return null;
            case "check":
                return this.carManager.check(Integer.parseInt(commandParams.get(0)));
            case "open":
                if (commandParams.size() == 5) {
                    this.carManager.open(
                            Integer.parseInt(commandParams.get(0)), commandParams.get(1),
                            Integer.parseInt(commandParams.get(2)), commandParams.get(3),
                            Integer.parseInt(commandParams.get(4)));
                } else {
                    this.carManager.open(
                            Integer.parseInt(commandParams.get(0)), commandParams.get(1),
                            Integer.parseInt(commandParams.get(2)), commandParams.get(3),
                            Integer.parseInt(commandParams.get(4)), Integer.parseInt(commandParams.get(5)));
                }
                return null;
            case "participate":
                this.carManager.participate(
                        Integer.parseInt(commandParams.get(0)), Integer.parseInt(commandParams.get(1)));
                return null;
            case "start":
                return this.carManager.start(Integer.parseInt(commandParams.get(0)));
            case "park":
                this.carManager.park(Integer.parseInt(commandParams.get(0)));
                return null;
            case "unpark":
                this.carManager.unpark(Integer.parseInt(commandParams.get(0)));
                return null;
            case "tune":
                this.carManager.tune(Integer.parseInt(commandParams.get(0)),
                        commandParams.get(1));
                return null;
            default:
                return null;
        }
    }
}
