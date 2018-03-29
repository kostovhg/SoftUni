package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "credit_card_number")
    private String creditCardNumber;

    @OneToMany(mappedBy = "customer")
    private Set<Sale> sales;

    public Customer(String name, String email, String creditCardNumber) {
        this.name = name;
        this.email = email;
        this.creditCardNumber = creditCardNumber;
    }

    public Customer() {
    }
}
