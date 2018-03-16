package lab.c_CarShopExtend;

public abstract class Car {

    protected static final Integer TIRES = 4;
    private String model;
    private String color;
    private Integer horsePower;
    protected String countryProduced;

    public Car(String model, String color, Integer horsePower, String countryProduced) {
        this.model = model;
        this.color = color;
        this.horsePower = horsePower;
        this.countryProduced = countryProduced;
    }

    public String getModel() {
        return this.model;
    }

    public String getColor() {
        return this.color;
    }

    public Integer getHorsePower() {
        return this.horsePower;
    }
}
