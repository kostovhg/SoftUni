package p06_BirthdayCelebrations;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Set<Creature> creatures = new LinkedHashSet<>();
        CreatureFactory factory = new CreatureFactory();

        String input = scanner.nextLine();

        while (!"End".equalsIgnoreCase(input)) {
            String[] tokens = input.split("\\s+");

            creatures.add(factory.createCreature(tokens));

            input = scanner.nextLine();
        }

        String year = scanner.next();

        for (Creature creature : creatures) {
            String birthdate = creature.getBirthdate();
            if (birthdate != null && birthdate.endsWith(year))
                System.out.println(birthdate);
        }
    }
}
