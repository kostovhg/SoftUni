package pr0304Barracks.interpreters;

import pr0304Barracks.contracts.CommandInterpreter;
import pr0304Barracks.contracts.Executable;
import pr0304Barracks.contracts.Repository;
import pr0304Barracks.contracts.UnitFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CommandInterpreterImpl implements CommandInterpreter {

    private static final String COMMANDS_PACKAGE_NAME =
            "pr0304Barracks.core.commands.";
    private static final String COMMAND_SUFFIX = "Command";

    @Override
    public Executable interpretCommand(String commandName) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String correctCommandName = String.valueOf(commandName.charAt(0)).toUpperCase() + commandName.substring(1);
        Class<?> commandClass;
        try {
            commandClass = Class.forName(COMMANDS_PACKAGE_NAME + correctCommandName + COMMAND_SUFFIX);
        } catch (ClassNotFoundException cnfe) {
            throw new RuntimeException("Invalid command!");
        }
        Constructor<?> constructor = commandClass.getDeclaredConstructor();
        Executable command = (Executable) constructor.newInstance();
        return command;
    }
}
