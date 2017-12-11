package logger.loggers;

import logger.LogType;
import logger.abstractClasses.Logger;

public class CombatLogger extends Logger {

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
