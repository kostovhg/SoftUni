package p05_BorderControl;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Set<Visitor> visitorSet = new LinkedHashSet<>();

        String input = scanner.nextLine();

        while(!"End".equalsIgnoreCase(input)){
            String[] tokens = input.split("\\s+");
            Visitor visitor = null;
            if(tokens.length == 2){
                visitor = new Robot(tokens);
            } else {
                visitor = new Citizen(tokens);
            }
            visitorSet.add(visitor);

            input = scanner.nextLine();
        }

        String fake = scanner.next();

        for (Visitor v :
                visitorSet) {
            String id = v.getId();
            if (id.endsWith(fake)){
                System.out.println(id);
            }
        }
    }
}
