package pr0304Barracks;

import pr0304Barracks.contracts.CommandInterpreter;
import pr0304Barracks.contracts.Repository;
import pr0304Barracks.contracts.Runnable;
import pr0304Barracks.contracts.UnitFactory;
import pr0304Barracks.core.Engine;
import pr0304Barracks.core.commands.Command;
import pr0304Barracks.core.factories.UnitFactoryImpl;
import pr0304Barracks.data.UnitRepository;
import pr0304Barracks.interpreters.CommandInterpreterImpl;

public class Main {

	public static void main(String[] args) {
		Repository repository = new UnitRepository();
		UnitFactory unitFactory = new UnitFactoryImpl();
		CommandInterpreter interpreter = new CommandInterpreterImpl();
		Runnable engine = new Engine(repository, unitFactory, interpreter);
		engine.run();
	}
}
