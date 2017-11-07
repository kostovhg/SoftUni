package app_Avatar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static app_Avatar.Factory.readLine;
import static app_Avatar.Constants.WARS_BY_NATIONS;

public class Main {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String input;

        while(!"Quit".equalsIgnoreCase(input = reader.readLine())){
            readLine(input);
        }

        for (int i = 0; i < WARS_BY_NATIONS.size(); i++) {
            System.out.println("War " + (i + 1) + " issued by " + WARS_BY_NATIONS.get(i));
        }
    }
}