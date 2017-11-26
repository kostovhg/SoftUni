package g_EqualityLogic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        Set<Person> orderedSet = new TreeSet<>();
        Set<Person> unorderedSed = new HashSet<>();

        int countOfInputs = Integer.parseInt(reader.readLine());

        for (int i = 0; i < countOfInputs; i++) {
            Person currentPerson = new Person(reader.readLine().split("\\s+"));
            orderedSet.add(currentPerson);
            unorderedSed.add(currentPerson);
        }

        System.out.println(orderedSet.size());
        System.out.println(unorderedSed.size());
    }
}