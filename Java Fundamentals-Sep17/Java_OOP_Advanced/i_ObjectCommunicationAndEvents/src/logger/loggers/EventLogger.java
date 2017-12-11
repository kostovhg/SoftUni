package logger.loggers;

import logger.LogType;
import logger.abstractClasses.Logger;

public class EventLogger extends Logger {

    @Override
    public void handle(LogType logtype, String message) {
        if (logtype == LogType.EVENT || logtype == LogType.TARGET) {
            System.out.println(logtype.name() + ": " + message);
        } else {
            super.passToSuccessor(logtype, message);
        }
    }

}
