package org.softuni.mostwanted.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "cars")
public class Car {

    private Integer id; //– integer number, primary identification field.
    private String brand; //– a string (required).
    private String model; //– a string (required).
    private BigDecimal price; //– a decimal data type.
    private Integer yearOfProduction; //– an integer number (required).
    private Double maxSpeed; //– a floating-point data type.
    private Double zeroToSixty; //– a floating-point data type.
    private Racer racer; //– a Racer entity.

    public Car() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "brand", nullable = false)
    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Column(name = "model", nullable = false)
    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "price", precision = 10, scale = 2)
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "year_of_production", nullable = false)
    public Integer getYearOfProduction() {
        return this.yearOfProduction;
    }

    public void setYearOfProduction(Integer yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    @Column(name = "max_speed", precision = 8, scale = 1)
    public Double getMaxSpeed() {
        return this.maxSpeed;
    }

    public void setMaxSpeed(Double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Column(name = "zero_to_sixty", precision = 6, scale = 2)
    public Double getZeroToSixty() {
        return this.zeroToSixty;
    }

    public void setZeroToSixty(Double zeroToSixty) {
        this.zeroToSixty = zeroToSixty;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "racer_id", foreignKey = @ForeignKey(name = "fk_car_racer"))
    public Racer getRacer() {
        return this.racer;
    }

    public void setRacer(Racer racer) {
        this.racer = racer;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Car))return false;
        Car otherCar = (Car)other;
        return this.getBrand().equalsIgnoreCase(otherCar.getBrand()) &&
                this.getModel().equalsIgnoreCase(otherCar.getModel()) &&
                this.getRacer().getName().equalsIgnoreCase(otherCar.getRacer().getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, racer.getName());
    }
}
