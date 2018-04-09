package AutoMappingExerciseApplication.terminal;

import AutoMappingExerciseApplication.terminal.commands.CommandInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final CommandInterpreter commandInterpreter;

    @Autowired
    public ConsoleRunner(CommandInterpreter commandInterpreter) {
        this.commandInterpreter = commandInterpreter;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(this.commandInterpreter.executeCommands());
    }
}