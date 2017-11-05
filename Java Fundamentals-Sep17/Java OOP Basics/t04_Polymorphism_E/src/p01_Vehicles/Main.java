package p01_Vehicles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String[] tokens = reader.readLine().split("\\s+");

        Vehicle car = new Car(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]));
        tokens = reader.readLine().split("\\s+");
        Vehicle truck = new Truck(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]));

        int commandsCount = Integer.parseInt(reader.readLine());

        for (int i = 0; i < commandsCount; i++) {
            tokens = reader.readLine().split("\\s+");
            switch (tokens[0].toLowerCase()) {
                case "drive":
                    if (tokens[1].equalsIgnoreCase("car")){
                        car.driving(Double.parseDouble(tokens[2]));
                    } else {
                        truck.driving(Double.parseDouble(tokens[2]));
                    }
                    break;
                case "refuel":
                    if (tokens[1].equalsIgnoreCase("car")){
                        car.refueling(Double.parseDouble(tokens[2]));
                    } else {
                        truck.refueling(Double.parseDouble(tokens[2]));
                    }
                    break;
            }
        }

        System.out.printf("Car: %.2f%n", car.getFuel());
        System.out.printf("Truck: %.2f%n", truck.getFuel());
    }
}