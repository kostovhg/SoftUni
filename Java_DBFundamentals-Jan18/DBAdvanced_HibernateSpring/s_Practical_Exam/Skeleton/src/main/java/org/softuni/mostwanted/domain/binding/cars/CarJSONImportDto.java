package org.softuni.mostwanted.domain.binding.cars;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

public class CarJSONImportDto implements Serializable {

    @Expose
    @NotEmpty
    private String brand; //– a string (required).


    @Expose
    @NotEmpty
    private String model; //– a string (required).

    @Expose
    private BigDecimal price; //– a decimal data type.

    @Expose
    @NotNull
    private Integer yearOfProduction; //– an integer number (required).

    @Expose
    private Double maxSpeed; //– a floating-point data type.

    @Expose
    private Double zeroToSixty; //– a floating-point data type.

    @Expose
    private String racerName; //– a Racer entity.

    public CarJSONImportDto() {
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getYearOfProduction() {
        return this.yearOfProduction;
    }

    public void setYearOfProduction(Integer yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public Double getMaxSpeed() {
        return this.maxSpeed;
    }

    public void setMaxSpeed(Double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Double getZeroToSixty() {
        return this.zeroToSixty;
    }

    public void setZeroToSixty(Double zeroToSixty) {
        this.zeroToSixty = zeroToSixty;
    }

    public String getRacerName() {
        return this.racerName;
    }

    public void setRacerName(String racerName) {
        this.racerName = racerName;
    }
}
