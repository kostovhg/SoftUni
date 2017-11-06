package p02_VehiclesExtension.Entities;

public class Car extends Vehicle {
    private static final double INCREASED_CONSUMPTION = 0.9;

    public Car(double[] args){
        super(args);
    }

    @Override
    public void driving(double km, String how){
        double necessaryFuel = km * (this.fuelConsumption + INCREASED_CONSUMPTION);
        if( necessaryFuel < this.fuelQuantity){
            this.fuelQuantity -= necessaryFuel;
            System.out.println("Car travelled " + getDoubleValue(km) + " km");
        } else {
            throw new IllegalArgumentException("Car needs refueling");
        }
    }
}
