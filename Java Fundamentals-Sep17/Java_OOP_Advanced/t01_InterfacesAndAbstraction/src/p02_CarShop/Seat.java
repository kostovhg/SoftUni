package p02_CarShop;

import java.io.Serializable;

public class Seat implements Car, Serializable {

    private String model;
    private String color;
    private Integer horsePower;

    private String countryProduced;

    public Seat(String model,
                String color,
                Integer horsePower,
                String countryProduced) {
        this.model = model;
        this.color = color;
        this.horsePower = horsePower;
        this.countryProduced = countryProduced;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public int getHorsePower() {
        return this.horsePower;
    }

    private String getCountryProduced() {
        return countryProduced;
    }

    @Override
    public String toString() {
        return String.format(
                "This is %s produced in %s and have %d tires",
                this.getModel(),
                this.getCountryProduced(),
                this.TIRES);
    }
}
