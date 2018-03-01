
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class s_PhonebookUpgrade {
    private static final Map<String, String> PHONEBOOK = new TreeMap<>();
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
                        System.out.println(contactToString(command[1]));
                    } else {
                        System.out.println("Contact " + command[1] + " does not exist.");
                    }
                    break;
                case "ListAll":
                    for (String contact : PHONEBOOK.keySet()) {
                        System.out.println(contactToString(contact));
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

    private static String contactToString(String name) {
        return String.format("%s -> %s", name, PHONEBOOK.get(name));
    }
}
