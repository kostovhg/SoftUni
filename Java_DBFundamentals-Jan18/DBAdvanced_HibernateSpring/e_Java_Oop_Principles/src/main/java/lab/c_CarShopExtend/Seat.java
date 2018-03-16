package lab.c_CarShopExtend;

public class Seat extends Sellable {

    public Seat(String model, String color, Integer horsePower, String countryProduced, Double price) {
        super(model, color, horsePower, countryProduced, price);
    }

    @Override
    public String toString() {
        return String.format("This is %s produced in %s and have %d tires",
                super.getModel(), super.countryProduced, TIRES);
    }
}
