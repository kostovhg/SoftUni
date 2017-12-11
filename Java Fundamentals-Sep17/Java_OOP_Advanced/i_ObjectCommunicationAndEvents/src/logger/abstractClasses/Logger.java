package logger.abstractClasses;

import logger.contracts.Handler;
import logger.enums.LogType;

/**
 *  A abstract class that implements {@link Handler} and contains in it
 *  the next Handler to deal with request, in case current one did not receive correct LogType
 *
 * @author Nikola Andreev
 * @see Handler
 * @since 1.0
 */

public abstract class Logger implements Handler {
    //

    /**
     * Contains the next handler in the chain.
     */
    private Handler successor;

    /**
     * Abstract method that will be implemented differently in concrete handlers
     * @param type of type {@linkplain LogType} to make decision for processing the message
     * @param message of type {@linkplain String} to be processed
     */
    public abstract void handle(LogType type, String message);

    /**
     * Setter for field <b>successor</b>
     * @param handler of type {@linkplain Handler} to be the next Handler to proceed the message
     */
    public void setSuccessor(Handler handler) {
        this.successor = handler;
    }

    /**
     * Method to transfer currently received LogType and message to the successor
     * @param type of type {@linkplain LogType} received LogType
     * @param message of type {@linkplain String} received message
     */
    public void passToSuccessor(LogType type, String message) {
        if(this.successor != null) {
            this.successor.handle(type, message);
        }
    }

}
