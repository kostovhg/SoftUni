package b_WarningLevels;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Logger logger = new Logger(Importance.valueOf(reader.readLine()));
        String input = null;

        while (!(input = reader.readLine()).equals("END")) {
            String[] tokens = input.split(":\\s+");
            Message newMessage = new Message(tokens[1]);
            logger.addMessage(Importance.valueOf(tokens[0]), newMessage);
        }

        for (Message msg : logger.getMessages()) {
            System.out.println(msg);
        }
    }
}