package hell;

import hell.controllers.HeroControllerImpl;
import hell.engine.Engine;
import hell.handlers.CommandHandlerImpl;
import hell.interfaces.*;
import hell.io.ConsoleInputReader;
import hell.io.ConsoleOutputWriter;
import hell.repositories.HeroRepository;

public class Main {
    public static void main(String[] args) {
        InputReader reader = new ConsoleInputReader();
        OutputWriter writer = new ConsoleOutputWriter();
        Repository<Hero> repository = new HeroRepository();
        HeroController controller = new HeroControllerImpl(repository);
        CommandHandler handler = new CommandHandlerImpl(controller);
        Engine engine = new Engine(reader, writer, handler);

        engine.run();

    }
}