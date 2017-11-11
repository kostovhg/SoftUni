package SystemSplit.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputReader {
    private final BufferedReader reader;

    public ConsoleInputReader() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String readLine() {
        String result = null;

        try{
            result = this.reader.readLine();
        }
        catch(IOException e) {}

        return result;
    }
}
