package f_ShoppingSpree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Person> persons = new LinkedHashMap<>();
        Map<String, Product> products = new HashMap<>();

        try {
            Arrays.asList(reader.readLine().split(";"))
                    .forEach(x -> {
                        Person p = new Person(x.split("="));
                        persons.put(p.getName(),p);
                    });
        } catch (IllegalArgumentException ise) {
            System.out.println(ise.getMessage());
            return;
        }

        try {
            Arrays.asList(reader.readLine().split(";"))
                    .forEach(x -> {
                        Product p = new Product(x.split("="));
                        products.put(p.getName(), p);
                    });
        } catch (IllegalArgumentException ise) {
            System.out.println(ise.getMessage());
            return;
        }

        String input;
        while(!"END".equals( input = reader.readLine())){
            String[] data = input.split("\\s+");
            try{
                persons.get(data[0]).addProduct(products.get(data[1]));
                System.out.println(String.format("%s bought %s", data[0], data[1]));
            } catch (IllegalStateException iae) {
                System.out.println(iae.getMessage());
            }
        }

        for (String person : persons.keySet()) {
            System.out.println(persons.get(person).userProducts());
        }

    }
}

/*
Create two classes: class Person and class Product. Each person should have a name, money and a bag of products. Each product should have name and cost. Name cannot be an empty string. Money cannot be a negative number.
Create a program in which each command corresponds to a person buying a product. If the person can afford a product add it to his bag and print message in format "[Person name] bought [Product name]". If a person doesn’t have enough money, print an appropriate message ("[Person name] can't afford [Product name]").
On the first two lines you are given all people and all products. After all purchases print every person in the order of appearance and all products that he has bought also in order of appearance. If nothing is bought, print the name of the person followed by "Nothing bought".
Read commands till you find line with "END" command. In case of invalid input (negative money exception message: "Money cannot be negative") or empty name: (empty name exception message "Name cannot be empty") break the program with an appropriate message. See the examples below:
 */