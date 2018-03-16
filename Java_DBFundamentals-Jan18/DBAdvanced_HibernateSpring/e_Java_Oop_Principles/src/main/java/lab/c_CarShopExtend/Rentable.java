package lab.c_CarShopExtend;

public abstract class Rentable extends Car {

    protected Integer minRentDays;
    protected Double pricePerDay;

    public Rentable(
            String model,
            String color,
            Integer horsePower,
            String countryProduced,
            Integer minRentDays,
            Double pricePerDay) {
        super(model, color, horsePower, countryProduced);
        this.minRentDays = minRentDays;
        this.pricePerDay = pricePerDay;
    }


    public Integer getMinRentDay(){
        return this.minRentDays;
    }
    public Double getPricePerDay(){
        return this.pricePerDay;
    }
}
