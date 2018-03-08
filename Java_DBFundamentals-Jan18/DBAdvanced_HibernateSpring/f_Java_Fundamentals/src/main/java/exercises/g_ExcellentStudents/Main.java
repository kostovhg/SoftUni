package exercises.g_ExcellentStudents;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input;
        //Map<String, List<Integer>> students = new LinkedHashMap<>();

        while (!"end".equalsIgnoreCase(input = reader.readLine())) {
            String[] tokens = input.split("\\s+");
            if (Arrays.stream(tokens)
                    .skip(2)
                    .anyMatch(x -> x.equals("6"))) {
                System.out.printf("%s %s%n", tokens[0], tokens[1]);
            }
        }
    }
}