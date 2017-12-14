package c_DependencyInversion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        PrimitiveCalculator calculator = new PrimitiveCalculator();

        String[] input;

        while (true) {
            input = reader.readLine().split("\\s+");

            if (input[0].equalsIgnoreCase("End")) {
                break;
            }

            switch (input[0]) {
                case "mode":
                    calculator.changeStrategy(input[1].toCharArray()[0]);
                    break;
                default:
                    System.out.println(calculator.
                            performCalculation(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
                    break;
            }
        }
    }
}
