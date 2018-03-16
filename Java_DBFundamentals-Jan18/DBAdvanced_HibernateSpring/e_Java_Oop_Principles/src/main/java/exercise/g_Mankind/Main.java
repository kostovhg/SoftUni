package exercise.g_Mankind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static exercise.g_Mankind.Constants.DELIMITER;

public class Main {

        public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Human student;
        Human worker;
        // Check together the creation of two objects
        try {
            student = new Student(reader.readLine().split(DELIMITER));
            worker = new Worker(reader.readLine().split(DELIMITER));
            // Print them if both are created successfully
            System.out.println(student);
            System.out.println(worker.toString().trim());
        } catch (IllegalArgumentException iae) {
            // If there is any input error, print it
            System.out.println(iae.getMessage());
        }
    }
}