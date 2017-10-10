import java.util.LinkedHashMap;
import java.util.Scanner;

public class p12_AMinerTask {
    private static LinkedHashMap<String, Integer> resources = new LinkedHashMap<>();

    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        String input;
        while(!(input = scann.nextLine()).equalsIgnoreCase("stop")){
            if(!resources.containsKey(input)){
                resources.put(input, Integer.parseInt(scann.nextLine()));
            } else {
                resources.put(input, resources.get(input) + Integer.parseInt(scann.nextLine()));
            }
        }
        for (String resource :
                resources.keySet()) {
            System.out.printf("%s -> %d%n", resource, resources.get(resource));
        }
    }
}