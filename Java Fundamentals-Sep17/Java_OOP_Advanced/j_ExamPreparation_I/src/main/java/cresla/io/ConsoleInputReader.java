package cresla.io;

import cresla.interfaces.InputReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputReader implements InputReader {

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public String readLine() {

        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
