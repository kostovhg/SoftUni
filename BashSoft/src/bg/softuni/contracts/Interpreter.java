package bg.softuni.contracts;

import java.io.IOException;

public interface Interpreter {

    void interpretCommand(String input) throws IOException;
}
