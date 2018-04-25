package car_dealer.entities;

import javax.persistence.*;

@Entity
@Table(name = "sales")
public class Sale {

    private Long id;
    private Double discount;
    private Car car;
    private Customer customer;

    public Sale() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT(11) UNSIGNED")
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "discount")
    public Double getDiscount() {
        return this.discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    public Car getCar() {
        return this.car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
