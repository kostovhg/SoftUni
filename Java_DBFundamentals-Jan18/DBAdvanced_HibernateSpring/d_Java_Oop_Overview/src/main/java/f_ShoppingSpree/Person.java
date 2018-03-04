package f_ShoppingSpree;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Person {

    private String name;
    private BigDecimal money;
    private List<Product> bagOfProducts;

    private Person() {
    }

    public Person(String name, String money) {
        this.setName(name);
        this.setMoney(money);
        this.setBagOfProducts();
    }

    public Person(String[] args) {
        this();
        if (args.length == 2) {
            this.setName(args[0]);
            this.setMoney(args[1]);
            this.setBagOfProducts();
        } else {
            throw new IllegalArgumentException("Money cannot be empty");
        }
    }

    private void setName(String name) throws IllegalArgumentException {
        if (name != null && !(name.trim()).isEmpty()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }

    private void setMoney(String money) throws IllegalArgumentException {
        double c = Double.parseDouble(money);
        if (c < 0) {
            throw new IllegalArgumentException("Money cannot be negative");
        } else {
            this.money = BigDecimal.valueOf(c);
        }
    }

    private void setBagOfProducts() {
        this.bagOfProducts = new ArrayList<Product>();
    }

    public String getName() {
        return this.name;
    }

    public BigDecimal getMoney() {
        return this.money;
    }

    public List<Product> getBagOfProducts() {
        return Collections.unmodifiableList(this.bagOfProducts);
    }

    public void addProduct(Product product) throws IllegalStateException {
        if (this.getMoney().subtract(product.getCost()).compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalStateException(String.format("%s can't afford %s", this.getName(), product.getName()));
        } else {
            this.money = (this.getMoney().subtract(product.getCost()));
            this.bagOfProducts.add(product);
        }
    }

    public String userProducts() {
        StringBuilder sb = new StringBuilder(this.getName());
        sb.append(" - ");
        if (this.bagOfProducts.size() > 0) {
            sb.append(String.join(", ", this.bagOfProducts.stream().map(Product::toString).collect(Collectors.toList())));
        } else {
            sb.append("Nothing bought");
        }

        return sb.toString();
    }
}
