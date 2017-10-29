import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p09_Google {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Person> persons = new HashMap<>();

        String input;
        while (!(input = reader.readLine()).equals("End")) {
            String[] tokens = input.split("\\s+");
            persons.putIfAbsent(tokens[0], new Person(tokens[0]));
            Person person = persons.get(tokens[0]);
            switch (tokens[1]) {
                case "company": person.setCompany(tokens);
                    break;
                case "pokemon": person.addPokemon(tokens);
                    break;
                case "parents": person.addParent(tokens);
                    break;
                case "children": person.addCild(tokens);
                    break;
                case "car": person.setCar(tokens);
                    break;
            }
        }

        System.out.println(persons.get(reader.readLine()).toString());
    }
}

class Person {
    private String name;
    private Company company = null;
    private Car car = null;
    private Set<String> pokemons = new LinkedHashSet<>();
    private Set<String> parents = new LinkedHashSet<>();
    private Set<String> children = new LinkedHashSet<>();

    public Person(String name) {
        this.name = name;
        this.pokemons.add("");
        this.parents.add("");
        this.children.add("");
    }

    public void setCompany(String[] tokens) {
        this.company = new Company(tokens);
    }

    public void setCar(String[] tokens) {
        this.car = new Car(tokens);
    }

    public void addPokemon(String[] tokens) {
        this.pokemons.add(String.format("%s %s%n", tokens[2], tokens[3]));
    }

    public void addParent(String[] tokens) {
        this.parents.add(String.format("%s %s%n", tokens[2], tokens[3]));
    }

    public void addCild(String[] tokens) {
        this.children.add(String.format("%s %s%n", tokens[2], tokens[3]));
    }

    @Override
    public String toString() {
        return String.format(
                "%s\nCompany:\n%sCar:\n%sPokemon:\n%sParents:\n%sChildren:\n%s",
                name,
                (company != null) ? company.toString() : "",
                (car != null) ? car.toString() : "",
                String.join("", pokemons),
                String.join("", parents),
                String.join("", children)
        );
    }
}

class Company {
    private String name;
    private String position;
    private double salary;

    public Company(String[] tokens) {
        this.name = tokens[2];
        this.position = tokens[3];
        this.salary = Double.parseDouble(tokens[4]);
    }

    @Override
    public String toString() {
        return String.format("%s %s %.2f%n", name, position, salary);
    }
}

class Car {
    private String model;
    private int speed;

    public Car(String[] tokens) {
        this.model = tokens[2];
        this.speed = Integer.valueOf(tokens[3]);
    }

    @Override
    public String toString() {
        return String.format("%s %d%n", model, speed);
    }
}
