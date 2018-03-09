package exercises.j_GroupByGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input;
        List<Person> persons = new ArrayList<>();

        while (!"end".equalsIgnoreCase(input = reader.readLine())) {
            String[] tokens = input.split("\\s+");
            persons.add(new Person(tokens));
        }

        persons.stream()
                .collect(
                        Collectors.groupingBy(Person::getGroup,
                                Collectors.mapping(
                                        Person::getName,
                                        Collectors.joining(", "))))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .forEach(kvp ->
                        System.out.printf(
                                "%d - %s%n",
                                kvp.getKey(),
                                kvp.getValue()));
    }
}