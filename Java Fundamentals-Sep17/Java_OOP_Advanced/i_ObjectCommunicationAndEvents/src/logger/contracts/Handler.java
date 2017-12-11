package logger.contracts;

import logger.enums.LogType;

/**
 *  In order to implement Chain of Responsibility {@link <a href="https://www.tutorialspoint.com/design_pattern/chain_of_responsibility_pattern.htm">
 *      Chain of Responsibility Pattern at tutorialspoint.com
 *      </a>} this contract defines two methods, one for setting the next handler
 *      and one for handle the request if it is of appropriated type.
 *
 *  <p>Handler is a contract for abstract {@link logger.abstractClasses.Logger}
 *  that manages different {@link logger.enums.LogType} </p>
 *
 *  @author Nikola Andreev
 *  @see logger.abstractClasses.Logger
 *  @since 1
 */

public interface Handler {

    /**
     * The method should process the message if type is correct
     * @param type of type {@linkplain LogType} to make decision for processing the message
     * @param message of type {@linkplain String} to be processed
     */
    void handle(LogType type, String message);

    /**
     * The method sets the next Handler to receive the message if LogType is not correct type
     * @param handler of type {@linkplain Handler} to be the next Handler to proceed the message
     */
    void setSuccessor(Handler handler);

}
