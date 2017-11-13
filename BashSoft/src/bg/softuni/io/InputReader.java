package bg.softuni.io;

import bg.softuni.StaticData.SessionData;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class InputReader {

    private static final String END_COMMAND = "quit";

    public static void readCommand() throws Exception {

        OutputWriter.writeMessage(String.format("%s > ", SessionData.currentPath));

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine().trim();

        while (!END_COMMAND.equals(input)) {
            CommandInterpreter.interpretCommand(input);
            OutputWriter.writeMessage(String.format("%s > ", SessionData.currentPath));

            input = reader.readLine().trim();
        }

        for (Thread thread : SessionData.threadPool) {
            thread.join();
        }
    }
}
