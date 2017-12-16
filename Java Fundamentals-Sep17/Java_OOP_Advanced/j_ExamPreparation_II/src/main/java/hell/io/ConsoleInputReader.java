package hell.io;


import hell.interfaces.InputReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputReader implements InputReader {

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public String readLine() {
        String input = null;
        try {
            input = reader.readLine();
        } catch (IOException e) {
            //
        }

        return input;
    }
}
