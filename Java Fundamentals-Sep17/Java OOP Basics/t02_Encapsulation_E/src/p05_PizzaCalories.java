import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class p05_PizzaCalories {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Pizza pizza = null;
        Dough dough = null;
        int count;

        String input = reader.readLine();
        String[] tokens = input.split("\\s+");

        try {
            count = Integer.parseInt(tokens[2]);
            pizza = new Pizza(tokens[1], count);
            tokens = reader.readLine().split("\\s+");
            dough = new Dough(tokens[1], tokens[2], Double.parseDouble(tokens[3]));
            pizza.setDough(dough);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }


        while(!(input = reader.readLine()).equalsIgnoreCase("END")) {
            tokens = input.split("\\s+");
            if(count > 0) {
                try {
                    pizza.addTopping(new Topping(tokens[1], Double.parseDouble(tokens[2])));
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    return;
                }
                count--;
            }
        }

        System.out.printf("%s - %.2f", pizza.getName(), pizza.calories());
    }
}

class Pizza {

    private String name;
    private Dough dough;
    private List<Topping> toppings;

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name.isEmpty() || name.length() > 15) {
            throw new IllegalArgumentException("Pizza name should be between 1 and 15 symbols.");
        }
        this.name = name;
    }

    void setDough(Dough dough) {
        this.dough = dough;
    }

    public void addTopping(Topping topping) {
        if (toppings.size() == 10) {
            throw new IllegalArgumentException("Number of toppings should be in range [0..10].");
        }
        toppings.add(topping);
    }

    public Pizza(String name, int total) {
        if (total > 10) {
            throw new IllegalArgumentException("Number of toppings should be in range [0..10].");
        }
        setName(name);
        toppings = new LinkedList<>();
    }

    public double calories() {
        double cal = 0.0;
        if(toppings.size() < 1) return 0.0;
        for (Topping topping : toppings) {
            cal += topping.getCalories();
        }
        return cal + dough.getCalories();
    }
}

class Dough {
    private String type; // white, wholegrain
    private String property; // crispy, chewy, homemade
    private double weight;
    private static final double DEFAULT_CALORIES = 2.0;
    private static final Map<String, Double> TYPE_MODIFIERS = createTypeMap();
    private static final Map<String, Double> PROPERTY_MODIFIERS = createPropertyMap();

    private static Map<String, Double> createTypeMap() {
        Map<String, Double> typeMap = new HashMap<>();
        typeMap.put("white", 1.5);
        typeMap.put("wholegrain", 1.0);
        return typeMap;
    }

    private static Map<String, Double> createPropertyMap() {
        Map<String, Double> propertyMap = new HashMap<>();
        propertyMap.put("crispy", 0.9);
        propertyMap.put("chewy", 1.1);
        propertyMap.put("homemade", 1.0);
        return propertyMap;
    }

    public Dough(String type, String property, double weight) {
        setType(type);
        setProperty(property);
        setWeight(weight);
    }

    private void setType(String t) {
        if (!TYPE_MODIFIERS.containsKey(t.toLowerCase())) {
            throw new IllegalArgumentException("Invalid type of dough.");
        }
        this.type = t.toLowerCase();
    }

    private void setProperty(String p) {
        if (!PROPERTY_MODIFIERS.containsKey(p.toLowerCase())) {
            throw new IllegalArgumentException("Invalid type of dough.");
        }
        this.property = p.toLowerCase();
    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 200) {
            throw new IllegalArgumentException("Dough weight should be in the range [1..200].");
        }
        this.weight = weight;
    }

    public double getCalories() {
        return (weight * DEFAULT_CALORIES) * TYPE_MODIFIERS.get(type) * PROPERTY_MODIFIERS.get(property);
    }
}

class Topping {
    private String type; // meat, veggies, cheese, sauce
    private double weight;
    private static final double DEFAULT_CALORIES = 2.0;
    private static final Map<String, Double> MODIFIERS = createMap();

    private static Map<String, Double> createMap() {
        Map<String, Double> def_map = new HashMap<>();
        def_map.put("meat", 1.2);
        def_map.put("veggies", 0.8);
        def_map.put("cheese", 1.1);
        def_map.put("sauce", 0.9);
        return def_map;
    }

    private void setType(String inType) {
        if (!MODIFIERS.containsKey(inType.toLowerCase())) {
            throw new IllegalArgumentException("Cannot place " + inType + " on top of your pizza.");
        }
        this.type = inType.toLowerCase();
    }

    private void setWeight(double inWeight) {
        if (inWeight < 1 || inWeight > 50) {
            throw new IllegalArgumentException(inWeight + "weight should be in the range [1..50].");
        }
        this.weight = inWeight;
    }

    Topping(String type, double weight) {
        setType(type);
        setWeight(weight);
    }

    double getCalories() {
        return weight * DEFAULT_CALORIES * MODIFIERS.get(type);
    }
}
