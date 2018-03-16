package exercise.h_Vehicles;

public class Truck extends Vehicle {

    public Truck(double fuelQuantity, double fuelConsumption) {
        super(fuelQuantity, fuelConsumption);
    }

    @Override
    protected void setFuelConsumption(double fuelConsumption) {
        super.setFuelConsumption(fuelConsumption + 1.6);
    }

    @Override
    public void refuel(double amount) {
        super.refuel((amount * 95) / 100);
    }
}
