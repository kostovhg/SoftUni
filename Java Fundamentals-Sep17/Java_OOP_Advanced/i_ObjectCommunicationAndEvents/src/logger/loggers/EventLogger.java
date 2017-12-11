package logger.loggers;

import logger.enums.LogType;
import logger.abstractClasses.Logger;

/**
 * Process events if events are EVENT or TARGET
 */
public class EventLogger extends Logger {

    /**
     *  Print message on the Console if LogType is EVENT or TARGET
     * @param logtype of type {@linkplain LogType} to be processed
     * @param message of type {@linkplain String} to be processed
     */
    @Override
    public void handle(LogType logtype, String message) {
        if (logtype == LogType.EVENT || logtype == LogType.TARGET) {
            System.out.println(logtype.name() + ": " + message);
        } else {
            super.passToSuccessor(logtype, message);
        }
    }

}
