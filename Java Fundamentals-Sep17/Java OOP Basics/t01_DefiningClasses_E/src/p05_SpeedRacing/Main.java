package p05_SpeedRacing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Car> cars = new LinkedHashMap<>();
        int count = Integer.parseInt(reader.readLine());
        for (int i = 0; i < count; i++) {
            String[] input = reader.readLine().split("\\s+");
            cars.put(input[0],new Car(input));
        }
        String input;
        while(!(input = reader.readLine()).equalsIgnoreCase("End")){
            String[] tokens = input.split("\\s+");
            String model = tokens[1];
            double kilometers = Double.parseDouble(tokens[2]);
            cars.get(model).tryTravelDistance(kilometers);
        }
        for (Car car : cars.values()) {
            car.printCar();
        }
    }
}

class Car{
    private String model;
    private double fuelAmount;
    private double fuelForKilometer;
    private double traveledKilometers = 0;

    Car(String[] tokens){
        this.model = tokens[0];
        this.fuelAmount = Double.parseDouble(tokens[1]);
        this.fuelForKilometer = Double.parseDouble(tokens[2]);
    }

    public void tryTravelDistance(double distance) {
        if(fuelAmount - distance*fuelForKilometer < 0.0)
            System.out.println("Insufficient fuel for the drive");
        else {
            fuelAmount -= distance * fuelForKilometer;
            traveledKilometers += distance;
        }
    }

    public void printCar(){
        System.out.printf("%s %.2f %.0f%n", model, fuelAmount, traveledKilometers);
    }
}
