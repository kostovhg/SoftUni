package logger.abstractClasses;

import logger.contracts.Handler;
import logger.*;

public abstract class Logger implements Handler {

    private Handler successor;

    public abstract void handle(LogType type, String message);

    public void setSuccessor(Handler handler) {
        this.successor = handler;
    }

    public void passToSuccessor(LogType type, String message) {
        if(this.successor != null) {
            this.successor.handle(type, message);
        }
    }

}
