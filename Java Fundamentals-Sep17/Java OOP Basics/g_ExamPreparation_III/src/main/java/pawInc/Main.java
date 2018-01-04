package pawInc;

import pawInc.commandHandler.PawIncCommandHandler;
import pawInc.contracts.*;
import pawInc.engines.PawIncEngine;
import pawInc.core.AnimalCenterManager;
import pawInc.io.ConsoleInputReader;
import pawInc.io.ConsoleOutputWriter;
import pawInc.utilities.CommandParser;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Reader reader = new ConsoleInputReader();
        Writer writer = new ConsoleOutputWriter();
        Parser parser = new CommandParser();
        Manager manager = new AnimalCenterManager(writer);
        Handler handler = new PawIncCommandHandler(manager);
        Engine engine = new PawIncEngine(reader, parser, handler, manager);

        engine.run();

    }
}
