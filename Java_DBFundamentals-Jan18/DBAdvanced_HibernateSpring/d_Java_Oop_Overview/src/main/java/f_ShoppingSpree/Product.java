package f_ShoppingSpree;

import java.math.BigDecimal;

public class Product {

    private String name;
    private BigDecimal cost;

    private Product(){}

    public Product(String name, String cost) throws IllegalArgumentException {
        this.setName(name);
        this.setCost(cost);
    }

    public Product(String[] args){
        this();
        this.setName(args[0]);
        if (args.length == 2) {
            this.setCost(args[1]);
        } else {
            throw new IllegalArgumentException("Cost cannot be empty");
        }
    }

    private void setName(String name) throws IllegalArgumentException{
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }

    private void setCost(String cost) throws IllegalArgumentException {
        double c = Double.parseDouble(cost);
        if(c < 0){
            throw new IllegalArgumentException("Money cannot be negative");
        } else {
            this.cost = BigDecimal.valueOf(c);
        }
    }

    public String getName() {
        return this.name;
    }

    public BigDecimal getCost() {
        return this.cost;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
