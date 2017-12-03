package pr0304Barracks.contracts;

import java.lang.reflect.InvocationTargetException;

public interface CommandInterpreter {

	Executable interpretCommand( String commandName) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
}
