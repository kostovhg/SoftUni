package p03_Mankind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        /*
        Ivan Ivanov 08
Pesho Kirov 1590 10
         */

        try {
            String[] tokens = reader.readLine().split("\\s+");
            Student student = new Student(tokens[0], tokens[1], tokens[2]);
            tokens = reader.readLine().split("\\s+");
            Worker worker = new Worker(tokens[0], tokens[1], Double.parseDouble(tokens[2]), Integer.parseInt(tokens[3]));
            System.out.println(student);
            System.out.println(worker);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}