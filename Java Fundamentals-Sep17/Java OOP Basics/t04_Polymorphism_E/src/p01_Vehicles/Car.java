package p01_Vehicles;

public class Car extends Vehicle {
    private static final double INCREASED_CONSUMPTION = 0.9;

    Car(double fuel, double consumption){
        super(fuel, consumption);
        this.increasedConsumption = INCREASED_CONSUMPTION;
    }

    @Override
    public void refueling(double litres) {
        this.fuelQuantity += litres;
    }

}
