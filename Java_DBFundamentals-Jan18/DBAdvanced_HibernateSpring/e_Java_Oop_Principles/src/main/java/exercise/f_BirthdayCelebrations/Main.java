package exercise.f_BirthdayCelebrations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Born> aliveBeings = new ArrayList<>();

        String input;

        while (!"end".equalsIgnoreCase(input = reader.readLine())) {
            String[] tokens = input.split("\\s+");
            Born being = null;
            switch (tokens[0]) {
                case "Citizen":
                    being = new Citizen(tokens);
                    break;
                case "Pet":
                    being = new Pet(tokens);
                    break;
                default:
                    continue;
            }
            aliveBeings.add(being);
        }

        String birthYear = reader.readLine();

        for (Born being: aliveBeings) {
            if (being.validateBirthYear(birthYear)) {
                System.out.println(being.getBirthday());
            }
        }
    }
}