package logger.loggers;

import logger.enums.LogType;
import logger.abstractClasses.Logger;

/**
 *  Logger that handles errors
 */
public class ErrorLogger extends Logger {

    /**
     * If LogType is ERROR print message on the console
     * @param logtype of type {@linkplain LogType} to be processed
     * @param message of type {@linkplain String} to be processed
     */
    @Override
    public void handle(LogType logtype, String message) {
        if (logtype == LogType.ERROR) {
            System.out.println(logtype.name() + ": " + message);
        } else {
            super.passToSuccessor(logtype, message);
        }
    }
}
