package p02_VehiclesExtension.Utils;

import p02_VehiclesExtension.Entities.*;

import java.util.Arrays;

public class Interpreter {
    // vehicle factory
    public static Vehicle getVehicle(String input){
        String sub = input.substring(input.indexOf(" ") + 1);
        double[] args = Arrays
                .stream(sub.split(" "))
                .mapToDouble(Double::parseDouble).toArray();
        switch(getType(input).toLowerCase()){
            case "car": return new Car(args);
            case "truck": return new Truck(args);
            case "bus": return new Bus(args);
        }
        return null;
    }

    private static String getType(String input){
        return input.substring(0, input.indexOf(" "));
    }

    public static void modifyVehicle(Vehicle vehicle, String[] args){
        Double amount = Double.parseDouble(args[2]);
        switch(args[0].toLowerCase()) {
            case "refuel":
                vehicle.refueling(amount);
                break;
            case "drive":
                vehicle.driving(amount, args[0]);
                break;
            case "driveempty":
                vehicle.driving(amount, args[0]);
        }
    }
}
