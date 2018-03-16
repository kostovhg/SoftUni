package exercise.g_Mankind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final String DELIMITER = "\\s+";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Human student = null;
        Human worker = null;
        String input;// = reader.readLine();
        //while(!input.equals("END")) {
            //System.out.println(input);
            try {
                input = reader.readLine();
                student = new Student(input.split(DELIMITER));
                input = reader.readLine();
                worker = new Worker(input.split(DELIMITER));
                System.out.println(student);
                System.out.println(worker.toString().trim());
            } catch (IllegalArgumentException iae) {
                System.out.println();
                System.out.println(iae.getMessage());
                input = reader.readLine();
            }
            //if(!input.startsWith("Try")){
                //input = reader.readLine();
            //}
        //}
    }
}