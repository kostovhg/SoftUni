package theExpanse.engines;

import theExpanse.core.ColonyManager;
import theExpanse.io.ConsoleInputReader;
import theExpanse.io.ConsoleOutputWriter;
import theExpanse.models.Colony;
import theExpanse.utilities.InputParser;

import java.util.List;

import static theExpanse.utilities.Constants.*;

public class Engine {

    private ConsoleInputReader reader;
    private ConsoleOutputWriter writer;
    private InputParser parser;
    private ColonyManager manager;

    public Engine(ConsoleInputReader reader, ConsoleOutputWriter writer, InputParser parser) {
        this.reader = reader;
        this.writer = writer;
        this.parser = parser;
    }

    public void run() {
        List<String> params = this.parser.parseInput(this.reader.readLine());
        this.manager = new ColonyManager(new Colony(Integer.parseInt(params.get(0)), Integer.parseInt(params.get(1))));

        while (true) {
            params = this.parser.parseInput(this.reader.readLine());
            String command = params.remove(0);
            if (TERMINATING_COMMAND.equals(command)) {
                break;
            }

            switch (command) {
                case INSERT_COMMAND:
                    this.manager.create(params);
                    break;
                case REMOVE_COMMAND:
                    this.manager.remove(params);
                    break;
                case GROW_COMMAND:
                    this.manager.grow(params);
                    break;
                case POTENTIAL_COMMAND:
                    this.writer.printLine(this.manager.getPotential(params));
                    break;
                case CAPACITY_COMMAND:
                    this.writer.printLine(this.manager.getCapacity(params));
                    break;
                case FAMILY_COMMAND:
                    this.writer.printLine(this.manager.getFamilyInfo(params));
                    break;
            }
        }
    }
}
