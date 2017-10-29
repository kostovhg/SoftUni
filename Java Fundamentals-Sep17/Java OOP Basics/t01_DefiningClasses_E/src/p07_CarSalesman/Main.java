package p07_CarSalesman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Engine> engines = new HashMap<>();
        List<Car> cars = new LinkedList<>();

        int count = Integer.parseInt(reader.readLine());

        for (int i = 0; i < count; i++) {
            String[] tokens = reader.readLine().split("\\s+");

            Engine engine = null;

            switch (tokens.length) {
                case 4:
                    engine = new Engine(tokens[0], tokens[1], tokens[2], tokens[3]);
                    break;
                case 3:
                    if (isNumber(tokens[2])) {
                        engine = new Engine(tokens[0], tokens[1], tokens[2]);
                    } else {
                        engine = new Engine(tokens[0], tokens[1], "n/a", tokens[2]);
                    }
                    break;
                case 2:
                    engine = new Engine(tokens[0], tokens[1]);
                    break;
            }
            engines.put(tokens[0], engine);
        }

        count = Integer.parseInt(reader.readLine());

        for (int i = 0; i < count; i++) {
            String[] tokens = reader.readLine().split("\\s+");

            Car car = null;
            Engine engine = engines.get(tokens[1]);

            switch (tokens.length) {
                case 4:
                    car = new Car(tokens[0], engine, tokens[2], tokens[3]);
                    break;
                case 3:
                    if (isNumber(tokens[2])) {
                        car = new Car(tokens[0], engine, tokens[2]);
                    } else {
                        car = new Car(tokens[0], engine, "n/a", tokens[2]);
                    }
                    break;
                case 2:
                    car = new Car(tokens[0], engine);
                    break;
            }
            cars.add(car);
        }

        for (Car car : cars) {
            car.printCar();
        }
    }

    private static boolean isNumber(String num) {
        try {
            Double.parseDouble(num);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}

class Car {

    private static final String DEFAULT_VALUE = "n/a";

    private String model;
    private Engine engine;
    private String weight;
    private String color;

    public Car(String model, Engine engine, String weight, String color) {
        this(model, engine);
        this.weight = weight;
        this.color = color;
    }

    public Car(String model, Engine engine, String weight) {
        this(model, engine);
        this.weight = weight;
    }

    public Car(String model, Engine engine) {
        this.model = model;
        this.engine = engine;
        this.weight = DEFAULT_VALUE;
        this.color = DEFAULT_VALUE;
    }

    public void printCar() {
        System.out.println(
                String.format(
                        "%s:\n" +
                                "  %s:\n" +
                                "    Power: %s\n" +
                                "    Displacement: %s\n" +
                                "    Efficiency: %s\n" +
                                "  Weight: %s\n" +
                                "  Color: %s",
                        model,
                        engine.getModel(),
                        engine.getPower(),
                        engine.getDisplacement(),
                        engine.getEfficiency(),
                        weight,
                        color
                )
        );
    }
}

 class Engine {

    private static final String DEFAULT_VALUE = "n/a";

    private String model;
    private String power;
    private String displacement;
    private String efficiency;

    public Engine(String model, String power, String displacement, String efficiency) {
        this.model = model;
        this.power = power;
        this.displacement = displacement;
        this.efficiency = efficiency;
    }

    public Engine(String model, String power, String displacement) {
        this.model = model;
        this.power = power;
        this.displacement = displacement;
        this.efficiency = DEFAULT_VALUE;
    }

    public Engine(String model, String power) {
        this.model = model;
        this.power = power;
        this.displacement = DEFAULT_VALUE;
        this.efficiency = DEFAULT_VALUE;
    }

    public String getModel() {
        return model;
    }

    public String getPower() {
        return power;
    }

    public String getDisplacement() {
        return displacement;
    }

    public String getEfficiency() {
        return efficiency;
    }
}