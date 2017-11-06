package p03_WildFarm;

import p03_WildFarm.Entities.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static List<Animal> animals = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        String input = reader.readLine();
        while(!input.equalsIgnoreCase("end")){
            Animal animal = null;
            String[] tokens = input.split("\\s+");
            switch (tokens[0].toLowerCase()){
                case "cat": animal = new Cat(tokens); break;
                case "tiger": animal = new Tiger(tokens); break;
                case "mouse": animal = new Mouse(tokens); break;
                case "zebra": animal = new Zebra(tokens); break;
            }
            if(animal != null) {
                animal.makeSound();
                try {
                    Food food = null;
                    tokens = reader.readLine().split("\\s+");
                    int amount = Integer.parseInt(tokens[1]);
                    switch (tokens[0].toLowerCase()) {
                        case "vegetable":
                            food = new Vegetables(amount);
                            break;
                        case "meat":
                            food = new Meat(amount);
                            break;
                    }
                    animal.eat(food);
                } catch (IllegalArgumentException iae) {
                    System.out.println(iae.getMessage() + Animal.UNAPPROPRIATED);
                }
                animals.add(animal);
            }
            input = reader.readLine();
        }

        for (Animal a : animals) {
            System.out.println(a);
        }
    }
}