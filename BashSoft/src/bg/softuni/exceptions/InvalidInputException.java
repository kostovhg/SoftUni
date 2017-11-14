package bg.softuni.exceptions;

public class InvalidInputException extends RuntimeException {
    private static final String NULL_OR_EMPTY_COMMAND = "The command has invalid arguments.";

    public InvalidInputException(){
        super(String.format(NULL_OR_EMPTY_COMMAND));
    }

    public InvalidInputException(String command) {
        super(String.format("The command '%s' is invalid", command));
    }
}