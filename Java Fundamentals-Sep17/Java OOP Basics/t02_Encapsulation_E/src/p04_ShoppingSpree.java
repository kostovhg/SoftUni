import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class p04_ShoppingSpree {
    private static Map<String, Person> clients = new LinkedHashMap<>();
    private static Map<String, Product> products = new LinkedHashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] personTokens = reader.readLine().split(";");
        for (String token : personTokens) {
            try {
                String name = token.substring(0, token.indexOf("=")).trim();
                double money = Double.parseDouble(token.substring(token.indexOf("=") + 1));
                clients.put(name, new Person(name, money));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return;
            }
        }

        String[] productTokens = reader.readLine().split(";");
        for (String token : productTokens) {
            try {
                String name = token.substring(0, token.indexOf("=")).trim();
                double money = Double.parseDouble(token.substring(token.indexOf("=") + 1));
                products.put(name, new Product(name, money));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return;
            }
        }

        String input;
        while(!(input = reader.readLine()).equalsIgnoreCase("END")){
            String[] tokens = input.split("\\s+");
            clients.get(tokens[0]).addProduct(products.get(tokens[1]));
        }

        for (Person person :
                clients.values()) {
            System.out.printf("%s - %s%n",
                    person.getName(),
                    person.getProducts());
        }
    }
}

class Person{
    private String name;
    private double money;
    private List<Product> products;

    public Person(String name, double money) {
        this.setName(name);
        this.setMoney(money);
        this.products = new LinkedList<>();
    }

    private void setName(String name) {
        if (name.isEmpty() || name.equals("\\s+")) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    void setMoney(double money) {
        if (money < 0.0) {
            throw new IllegalArgumentException("Money publiccannot be negative");
        }
        this.money = money;
    }

     String getProducts(){
        if (products.size() == 0)
            return "Nothing bought";
        return String.join(", ", products.stream().map(Product::getName).collect(Collectors.toList()));
        //return Collections.unmodifiableList(products);
    }

    public void addProduct(Product product){
        if (product.getCost() > money) {
            System.out.printf("%s can't afford %s%n", name, product.getName());
        } else {
            products.add(product);
            System.out.printf("%s bought %s%n", name, product.getName());
            this.money -= product.getCost();
        }
    }


}

class Product{

    private String name;
    private double cost;

    private void setName(String name) {
        if (name.isEmpty() || name.equals("\\s+")) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    private void setCost(double cost) {
        if (cost < 0.0) {
            throw new IllegalArgumentException("Money cannot be negative");
        }
        this.cost = cost;
    }

    public Product(String name, double cost) {
        setName(name);
        setCost(cost);
    }

    public double getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

}
