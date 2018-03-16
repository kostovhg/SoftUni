package lab.c_CarShopExtend;

public class Audi extends Rentable {

    public Audi(
            String model,
            String color,
            Integer horsePower,
            String countryProduced,
            Integer minRentDays,
            Double pricePerDay) {
        super(model, color, horsePower, countryProduced, minRentDays, pricePerDay);
    }

    @Override
    public String toString() {
        return String.format("This is %s produced in %s and have %d tires",
                super.getModel(), super.countryProduced, TIRES);
    }
}
