package car_dealer.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "parts")
public class Part {

    private Long id;
    private String name;
    private BigDecimal price;
    private int quantity;
    private Supplier supplier;
    private Set<Car> cars;

    public Part() {
        this.cars = new HashSet<>();
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

    @Column(name = "name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "price")
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "quantity")
    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    public Supplier getSupplier() {
        return this.supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @ManyToMany(mappedBy = "parts", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<Car> getCars() {
        return this.cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }
}
