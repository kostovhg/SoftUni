package entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private double quantity;

    @Column(name = "price")
    private BigDecimal price;

    @OneToMany(mappedBy = "product")
    private Set<Sale> sales;

    public Product(String name, double quantity, BigDecimal price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public Product() {
    }
}
