package helpers.reader;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpReaderImpl implements HttpReader {

    private final BufferedReader reader;

    public HttpReaderImpl(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public String readLine() throws IOException {
        return reader.readLine();
    }

    @Override
    public String readHttpRequest() throws IOException {

        StringBuilder input = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null){
            input.append(line).append(System.lineSeparator());
            if(line.isEmpty()) {
                break;
            }
        }
        return input.toString();
    }
}
