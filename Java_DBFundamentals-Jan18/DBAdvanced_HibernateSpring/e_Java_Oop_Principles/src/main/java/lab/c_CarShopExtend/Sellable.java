package lab.c_CarShopExtend;

public abstract class Sellable extends Car {

    protected Double price;

    public Sellable(String model, String color, Integer horsePower, String countryProduced, Double price) {
        super(model, color, horsePower, countryProduced);
        this.price = price;
    }
}
