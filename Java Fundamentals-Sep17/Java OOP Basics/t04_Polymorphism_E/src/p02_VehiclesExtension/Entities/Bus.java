package p02_VehiclesExtension.Entities;

public class Bus extends Vehicle {

    private static final double INCREASED_CONSUMPTION = 1.4;

    public Bus(double[] args) {
        super(args);
    }

    @Override
    public void driving(double km, String how){
        double necessaryFuel = km * (this.fuelConsumption  +
                (how.equalsIgnoreCase("driveempty") ?
                        0 :
                        INCREASED_CONSUMPTION ));
        if( necessaryFuel < this.fuelQuantity){
            this.fuelQuantity -= necessaryFuel;
            System.out.println("Bus travelled " + getDoubleValue(km) + " km");
        } else {
            throw new IllegalArgumentException("Bus needs refueling");
        }
    }
}
