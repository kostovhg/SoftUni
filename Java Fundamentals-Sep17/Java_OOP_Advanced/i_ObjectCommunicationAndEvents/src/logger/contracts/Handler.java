package logger.contracts;

import logger.LogType;

public interface Handler {

    void handle(LogType type, String message);

    void setSuccessor(Handler handler);

}
