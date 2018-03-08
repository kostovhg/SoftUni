package exercises.f_FilterStudentsByPhone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input;
        List<String[]> students = new ArrayList<>();

        while (!"end".equalsIgnoreCase(input = reader.readLine())) {
            String[] tokens = input.split("\\s+");
            students.add(tokens);
        }

        students.stream()
                .filter(s -> s[2].startsWith("02") || s[2].startsWith("+3592"))
                .forEach(s -> System.out.printf("%s %s%n", s[0], s[1]));
    }
}