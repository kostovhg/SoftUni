package i_Students;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = null;

        while (!"End".equals(input = reader.readLine())) {
            Student s = new Student(input);
        }

        System.out.println(Student.getStudentsCount());
    }
}