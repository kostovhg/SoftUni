package p03_OpinionPoll;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Set<Person> persons = new LinkedHashSet<>();
        int count = Integer.parseInt(reader.readLine());
        String[] input;
        for (int i = 0; i < count; i++) {
            input = reader.readLine().split("\\s+");
            persons.add(new Person(input[0], Integer.valueOf(input[1])));
        }
        persons.stream()
                .filter(x -> x.getAge() > 30)
                .sorted(Comparator.comparing(Person::getName))
                .forEach(x ->
                        System.out.printf("%s - %d%n", x.getName(), x.getAge()));
    }
}

class Person {
    private String name;
    private int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
