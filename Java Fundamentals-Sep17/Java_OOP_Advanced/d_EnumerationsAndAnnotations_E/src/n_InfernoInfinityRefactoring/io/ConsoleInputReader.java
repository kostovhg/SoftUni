package n_InfernoInfinityRefactoring.io;

import java.io.BufferedReader;
import java.io.IOException;

public class ConsoleInputReader implements InputReader{

    private BufferedReader reader;

    public ConsoleInputReader(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public String readLine() {
        String line = null;
        try {
            line = this.reader.readLine();
        } catch (IOException ignored) {

        }
        return line;
    }
}
