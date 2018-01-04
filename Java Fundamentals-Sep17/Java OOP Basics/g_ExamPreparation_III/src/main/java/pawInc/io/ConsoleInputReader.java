package pawInc.io;

import pawInc.contracts.Reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputReader implements Reader {

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public String readLine() throws IOException {
        return this.reader.readLine();
    }
}
