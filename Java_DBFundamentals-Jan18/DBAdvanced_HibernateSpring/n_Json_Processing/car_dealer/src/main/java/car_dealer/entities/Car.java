package car_dealer.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Car {

    private Long id;
    private String make;
    private String model;
    private Long travelledDistance;
    private Sale sale;
    private Set<Part> parts;
    private BigDecimal price;

    public Car() {
        this.parts = new HashSet<>();
        this.price = BigDecimal.ZERO;
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

    @Column(name = "make")
    public String getMake() {
        return this.make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @Column(name = "model")
    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "travelled_distance")
    public Long getTravelledDistance() {
        return this.travelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    @OneToOne(mappedBy = "car")
    public Sale getSale() {
        return this.sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "parts_cars",
            joinColumns =
            @JoinColumn(name = "car_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "part_id", referencedColumnName = "id"))
    public Set<Part> getParts() {
        return this.parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }

    @Column(name = "price")
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void addPart(Part part){
        this.parts.add(part);
        this.price = this.price.add(part.getPrice());
    }

}
