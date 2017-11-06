package p02_VehiclesExtension.Entities;

public class Truck extends Vehicle {
    private static final double INCREASED_CONSUMPTION = 1.6;
    private static final double FUEL_LEAKAGE = 0.95;

    public Truck(double[] args) {
        super(args);
    }

    @Override
    public void driving(double km, String how){
        double necessaryFuel = km * (this.fuelConsumption + INCREASED_CONSUMPTION);
        if( necessaryFuel < this.fuelQuantity){
            this.fuelQuantity -= necessaryFuel;
            System.out.println("Truck travelled " + getDoubleValue(km) + " km");
        } else {
            throw new IllegalArgumentException("Truck needs refueling");
        }
    }

    @Override
    public void refueling(double litres) {
        if (litres > tankCapacity) {
            throw new IllegalArgumentException("Cannot fit fuel in tank");
        }
        if ( litres <= 0) {
            throw new IllegalArgumentException("Fuel must be a positive number");
        }
        this.fuelQuantity += litres * FUEL_LEAKAGE;
    }
}
