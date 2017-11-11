package SystemSplit.engines;

import SystemSplit.core.Manager;
import SystemSplit.io.ConsoleInputReader;
import SystemSplit.io.ConsoleOutputWriter;
import SystemSplit.utilities.InputParser;

import java.util.List;

import static SystemSplit.utilities.Constants.*;

public class Engine {

    private final ConsoleInputReader inputReader;
    private final ConsoleOutputWriter outputWriter;
    private final InputParser inputParser;
    private final Manager manager;

    public Engine(ConsoleInputReader inputReader, ConsoleOutputWriter outputWriter, InputParser inputParser, Manager manager) {
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
        this.inputParser = inputParser;
        this.manager = manager;
    }

    public void run() {
        String inputLine;

        while (true) {
            inputLine = this.inputReader.readLine();
            if (inputLine.equals(INPUT_TERMINATING_COMMAND)) {
                break;
            }
            String result = this.dispatchCommand(this.inputParser.parseInput(inputLine));
            if(result != null){
                outputWriter.writeLine(result);
            }
        }
        outputWriter.writeLine(manager.toString());
    }

    private String dispatchCommand(List<String> commandParams) {
        String command = commandParams.remove(0);

        switch (command) {
            case REGISTER_POWER_HARDWARE:
                this.manager.registerPowerHardware(
                        commandParams.get(0),
                        Integer.parseInt(commandParams.get(1)),
                        Integer.parseInt(commandParams.get(2)));
                return null;
            case REGISTER_HEAVY_HARDWARE:
                this.manager.registerHeavyHardware(
                        commandParams.get(0),
                        Integer.parseInt(commandParams.get(1)),
                        Integer.parseInt(commandParams.get(2))
                );
                return null;
            case REGISTER_EXPRESS_SOFTWARE:
                this.manager.registerExpressSoftware(
                        commandParams.get(0),
                        commandParams.get(1),
                        Integer.parseInt(commandParams.get(2)),
                        Integer.parseInt(commandParams.get(3))
                );
                return null;
            case REGISTER_LIGHT_SOFTWARE:
                this.manager.registerLightSoftware(
                        commandParams.get(0),
                        commandParams.get(1),
                        Integer.parseInt(commandParams.get(2)),
                        Integer.parseInt(commandParams.get(3))
                );
                return null;
            case RELEASE_SOFTWARE_COMPONENT:
                this.manager.releaseSoftwareComponent(
                        commandParams.get(0),
                        commandParams.get(1)
                );
                return null;
            case ANALYZE:
                return this.manager.analyze();
            case DUMP:
                this.manager.dump(commandParams.get(0));
                return null;
            case RESTORE:
                this.manager.restore(commandParams.get(0));
                return null;
            case DESTROY:
                this.manager.destroy(commandParams.get(0));
                return null;
            case DUMP_ANALYZE:
                return this.manager.dumpAnalyze();
            default:
                return null;
        }
    }
}
