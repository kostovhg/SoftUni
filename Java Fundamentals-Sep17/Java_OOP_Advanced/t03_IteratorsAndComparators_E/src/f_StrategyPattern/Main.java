package f_StrategyPattern;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        Set<Person> sortedByName = new TreeSet<>(new CompareByName());
        Set<Person> sortedByAge = new TreeSet<>(new CompareByAge());
        int personsCount = Integer.parseInt(reader.readLine());

        for (int i = 0; i < personsCount; i++) {
            Person currentPerson = new Person(reader.readLine().split("\\s+"));
            sortedByName.add(currentPerson);
            sortedByAge.add(currentPerson);
        }

        sortedByName.forEach(System.out::println);
        sortedByAge.forEach(System.out::println);
    }
}