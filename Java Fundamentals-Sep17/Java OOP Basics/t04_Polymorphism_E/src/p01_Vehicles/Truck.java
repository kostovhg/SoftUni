package p01_Vehicles;

public class Truck extends Vehicle {
    private static final double INCREASED_CONSUMPTION = 1.6;

    Truck(double fuel, double consumption) {
        super(fuel, consumption);
        this.increasedConsumption = INCREASED_CONSUMPTION;
    }

    @Override
    public void refueling(double litres) {
        this.fuelQuantity += (litres  * 95) / 100;
    }
}
