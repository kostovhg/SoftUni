package p06_RawData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {

    private static BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in)
    );
    private static Map<String, Car> cars = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {
        int count = Integer.parseInt(reader.readLine());
        String[] input;
        for (int i = 0; i < count; i++) {
            input = reader.readLine().split("\\s+");
            cars.put(input[0], new Car(input));
        }
        if(reader.readLine().equalsIgnoreCase("fragile")){
            cars.entrySet().stream()
                    .filter(x -> x.getValue().matchCargoType("fragile"))
                    .filter(x -> x.getValue().anyTirePressureLowerThan(1))
                    .forEach(x -> System.out.println(x.getKey()));
        } else {
            cars.entrySet().stream()
                    .filter(x -> x.getValue().matchCargoType("flamable")
                            && x.getValue().getEnginePower() > 250)
                    .forEach(x -> System.out.println(x.getKey()));
        }
    }
}

class Car {
    private String model;
    private Engine engine;
    private Cargo cargo;
    private Tire[] tires = new Tire[4];

    boolean anyTirePressureLowerThan(int pressure) {
        return Arrays.stream(tires).anyMatch(p -> p.getPressure() < pressure);
    }

    boolean matchCargoType(String type) {
        return this.cargo.getType().equalsIgnoreCase(type);
    }

    int getEnginePower(){
        return this.engine.getPower();
    }

    Car(String[] tokens){
        this.model = tokens[0];
        this.engine = new Engine(tokens[1], tokens[2]);
        this.cargo = new Cargo(tokens[3], tokens[4]);
        for (int i = 0, j = 5; i < 4; i++, j += 2) {
            this.tires[i] = new Tire(tokens[j], tokens[j + 1]);
        }
    }
}

class Engine {
    private int speed;
    private int power;

    Engine(String speed, String power) {
        this.speed = Integer.parseInt(speed);
        this.power = Integer.parseInt(power);
    }

    int getPower() {
        return power;
    }
}

class Tire {
    private int age;
    private double pressure;

    double getPressure(){
        return this.pressure;
    }

    Tire(String pressure, String age) {
        this.age = Integer.parseInt(age);
        this.pressure = Double.parseDouble(pressure);
    }
}

class Cargo {
    private int weight;
    private String type;

    String getType() { return this.type; }

    Cargo(String weight, String type) {
        this.weight = Integer.parseInt(weight);
        this.type = type;
    }
}