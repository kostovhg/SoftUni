package bg.softuni.exceptions;

public class InvalidInputException extends Throwable {
    public InvalidInputException(String input) {
    }
}

/*
    public static final String NULL_OR_EMPTY_COMMAND = "The command has invalid arguments.";

    public InvalidCommandException(){
        super(String.format(NULL_OR_EMPTY_COMMAND));
    }

    public InvalidCommandException(String command) {
        super(String.format("The command '%s' is invalid", command));
    }
 */
