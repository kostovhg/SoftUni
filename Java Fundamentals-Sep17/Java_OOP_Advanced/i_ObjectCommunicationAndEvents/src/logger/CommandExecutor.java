package logger;

import logger.contracts.Command;
import logger.contracts.Executor;

public class CommandExecutor implements Executor {

    @Override
    public void executeCommand(Command command) {
        command.execute();
    }
}
