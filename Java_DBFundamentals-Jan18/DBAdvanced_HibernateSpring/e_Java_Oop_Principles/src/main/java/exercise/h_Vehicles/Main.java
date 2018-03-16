package exercise.h_Vehicles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Vehicle car = getData(reader.readLine());
        Vehicle truck = getData(reader.readLine());

        int commandsCount = Integer.parseInt(reader.readLine());

        for (int i = 0; i < commandsCount; i++) {
            String[] input = reader.readLine().split("\\s+");
            String result = null;
            switch (input[1]) {
                case "Car":
                    result = useVehicle(car, input[0], Double.parseDouble(input[2]));
                    break;
                case "Truck":
                    result = useVehicle(truck, input[0], Double.parseDouble(input[2]));
                    break;
            }
            if (result != null) {
                System.out.println(String.format("%s %s", input[1], result));
            }
        }

        System.out.println(String.format("Car: %.2f", car.getFuelQuantity()));
        System.out.println(String.format("Truck: %.2f", truck.getFuelQuantity()));

    }

    private static String useVehicle(Vehicle v, String s, double d) {
        switch (s) {
            case "Drive":
                return v.drive(d);
            case "Refuel":
                v.refuel(d);
                return null;
            default:
                return null;
        }
    }

    private static Vehicle getData(String input) {
        String[] data = input.split("\\s+");
        switch (data[0]) {
            case "Car":
                return new Car(
                        Double.parseDouble(data[1]),
                        Double.parseDouble(data[2]));
            case "Truck":
                return new Truck(
                        Double.parseDouble(data[1]),
                        Double.parseDouble(data[2]));
            default:
                return null;
        }
    }
}