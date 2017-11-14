package bg.softuni.exceptions;

public class InvalidCommandException extends RuntimeException {

    private static final String NULL_OR_EMPTY_COMMAND = "The command has invalid arguments.";

    public InvalidCommandException(){
        super(NULL_OR_EMPTY_COMMAND);
    }

    public InvalidCommandException(String command) {
        super(String.format("The command '%s' is invalid", command));
    }
}
