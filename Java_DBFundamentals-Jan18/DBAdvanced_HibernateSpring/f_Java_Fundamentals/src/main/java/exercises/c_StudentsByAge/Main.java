package exercises.c_StudentsByAge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input;
        Map<String, Integer> students = new LinkedHashMap<>();

        while (!"end".equalsIgnoreCase(input = reader.readLine())) {
            String[] tokens = input.split("\\s+");
            students.put(String.format("%s %s", tokens[0], tokens[1]), Integer.valueOf(tokens[2]));
        }

        students.entrySet()
                .stream()
                .filter(kvp -> kvp.getValue() >= 18 && kvp.getValue() <= 24)
                .forEach(kvp -> System.out.printf("%s %s%n", kvp.getKey(), kvp.getValue()));
    }
}