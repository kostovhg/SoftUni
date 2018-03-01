import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class r_Phonebook {

    private static final Map<String, String> PHONEBOOK = new LinkedHashMap<>();
    //private static final String MATCH_PATTERN = "A \\\\w+ [\\\\+\\\\/\\\\(\\\\)0-9]+";

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] command = parseCommand(reader.readLine());

        while (!command[0].equals("END")) {
            switch (command[0]){
                case "A":
                    PHONEBOOK.put(command[1], command[2]);
                    break;
                case "S":
                    if(PHONEBOOK.containsKey(command[1])){
                        System.out.println(command[1] + " -> " + PHONEBOOK.get(command[1]));
                    } else {
                        System.out.println("Contact " + command[1] + " does not exist.");
                    }
                    break;
                default:
                    System.out.println("No such action!");
            }
            command = parseCommand(reader.readLine());
        }
    }

    private static String[] parseCommand(String input){
        return input.split("\\s+");
    }
}
