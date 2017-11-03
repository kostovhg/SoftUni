package p06_Animals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        String input;
        String[] tokens;
        while (!(input = reader.readLine()).equalsIgnoreCase("Beast!")) {
            tokens = reader.readLine().split("\\s+");

            try {
                Animal animal = getAnimal(input, tokens);
                // ??? why
                // System.out.println(animal);
                // doesn't pass last test
                System.out.println(animal.toString());
            } catch (Exception e) {
                System.out.println(ErrorMessageContants.INVALID_INPUT_MESSAGE);
            }
        }
    }

    private static Animal getAnimal(String input, String[] tokens) {
        switch (input.toLowerCase()) {
            case "animal":
                return new Animal(tokens);
            case "dog":
                return new Dog(tokens);
            case "cat":
                return new Cat(tokens);
            case "kitten":
                return new Kitten(tokens);
            case "tomcat":
                return new Tomcat(tokens);
            case "frog":
                return new Frog(tokens);
        }
        return null;
    }
}