import java.util.HashMap;
import java.util.Scanner;

public class p11_Phonebook {
    
    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        HashMap<String, String> phonebook = new HashMap<>();
        String input;
        while(!(input = scann.nextLine()).equals("search")){
            String[] tokens = input.split("-");
            phonebook.put(tokens[0], tokens[1]);
        }
        while(!(input = scann.nextLine()).equalsIgnoreCase("stop")){
            if(phonebook.containsKey(input)){
                System.out.printf("%s -> %s%n", input, phonebook.get(input));
            } else {
                System.out.printf("Contact %s does not exist.%n", input);
            }
        }
    }
}