package exercise.e_BorderControl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Identifiable> borderCrossers = new ArrayList<>();
        List<Identifiable> detainedCrossers = new ArrayList<>();

        String input;

        while (!"end".equalsIgnoreCase(input = reader.readLine())){
            String[] tokens = input.split("\\s+");
            Identifiable crosser = null;
            if(tokens.length == 3) {
                crosser = new Citizen(tokens);
            } else if (tokens.length == 2) {
                crosser = new Robot(tokens);
            }
            borderCrossers.add(crosser);
        }

        String idEnding = reader.readLine();

        for (Identifiable borderCrosser : borderCrossers) {
            if (borderCrosser.validateID(idEnding)) {
                detainedCrossers.add(borderCrosser);
                System.out.println(borderCrosser.getId());
            }
        }
    }
}