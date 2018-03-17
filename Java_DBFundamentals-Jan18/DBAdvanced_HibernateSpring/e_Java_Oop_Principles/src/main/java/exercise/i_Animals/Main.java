package exercise.i_Animals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static exercise.i_Animals.Constants.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //List<Animal> animals = new ArrayList<>();
        Animal animal;
        String input;
        while (!"Beast!".equals(input = reader.readLine())) {

            // String[] tokens = reader.readLine().split("\\s+");
            try {
                Classes type = Classes.valueOf(input.toUpperCase());
                //animals.add(AnimalFactory.newAnimal(type, reader.readLine()));
                animal = AnimalFactory.newAnimal(type, reader.readLine());
                //System.out.println(animals.get(animals.size() - 1).toString());
                System.out.println(animal);
            } catch (IllegalArgumentException iae) {
                System.out.println(INVALID_INPUT);
            }

        }
    }
}

/*
Constraints
 Each Animal should have name, age and gender
 All properties’ values should not be blank (e.g. name, age and so on…)
 If you enter invalid input for one of the properties’ values, throw exception with message: “Invalid input!”
 Each animal should have a functionality to produceSound()
 Here is example of what each kind of animal should produce when, produceSound() is called
o Dog: “BauBau”
o Cat: “MiauMiau”
o Frog: “Frogggg”
o Kitten: “Miau”
o Tomcat: “Give me one million b***h”
o Message from the Animal class: &quot;Not implemented!&quot;

Note:

Kitten and Tomcat have default gender not from input?! So tokens can be with 2 inputs
 */