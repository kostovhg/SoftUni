package p02_VehiclesExtension;

import p02_VehiclesExtension.Entities.*;
import p02_VehiclesExtension.Utils.Interpreter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static Map<String, Vehicle> vehicles = new LinkedHashMap<>();
    private static String[] getTokens(String line) {
        return line.split("\\s+");
    }

    public static void main(String[] args) throws Exception {
        String[] tokens;

        for (int i = 0; i < 3; i++) {
            Vehicle vehicle = Interpreter.getVehicle(br.readLine());
            if (vehicle != null) {
                vehicles.put(vehicle.getClass().getSimpleName(), vehicle);
            }
        }

        int commandsCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < commandsCount; i++) {
            tokens = getTokens(br.readLine());
            try {
                Interpreter.modifyVehicle(vehicles.get(tokens[1]), tokens);
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
            }
        }

        for (Vehicle v :
                vehicles.values()) {
            System.out.printf("%s: %.2f%n", v.getClass().getSimpleName(), v.getFuel());
        }
    }


}