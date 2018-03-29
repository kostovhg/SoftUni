package entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private StoreLocation storeLocation;

    @Column(name = "date")
    private Date date;

    public Sale(Product product, Customer customer, StoreLocation storeLocation, Date date) {
        this.product = product;
        this.customer = customer;
        this.storeLocation = storeLocation;
        this.date = date;
    }

    public Sale() {
    }
}
