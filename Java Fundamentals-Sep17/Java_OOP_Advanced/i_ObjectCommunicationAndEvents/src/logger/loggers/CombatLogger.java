package logger.loggers;

import logger.enums.LogType;
import logger.abstractClasses.Logger;

/**
 *  Logger for attacking targets
 */
public class CombatLogger extends Logger {

    /**
     * Concrete implementation prints a message on the Console
     * if {@link LogType} is ATTACK or MAGIC
     * @param logtype of type {@linkplain LogType} to be processed
     * @param message of type {@linkplain String} to be processed
     */
    @Override
    public void handle(LogType logtype, String message) {
        if (logtype == LogType.ATTACK || logtype == LogType.MAGIC) {
            System.out.println(logtype.name() + ": " + message);
        } else {
            if (logtype != null) {
                super.passToSuccessor(logtype, message);
            }
        }
    }
}
