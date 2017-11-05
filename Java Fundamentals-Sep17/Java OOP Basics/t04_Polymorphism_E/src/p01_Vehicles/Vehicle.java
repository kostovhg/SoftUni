package p01_Vehicles;

import java.text.DecimalFormat;

abstract public class Vehicle {
    double fuelQuantity;
    private double fuelConsumption;
    double increasedConsumption;

    Vehicle(double fuel, double consumption) {
        this.setFuel(fuel);
        this.setConsumption(consumption);
    }

    void driving(double km){
        double necessaryFuel = km * (this.fuelConsumption + increasedConsumption);
        if( necessaryFuel < this.fuelQuantity){
            this.fuelQuantity -= necessaryFuel;
            System.out.println(this.getClass().getSimpleName() + " travelled " + getDoubleValue(km) + " km");
        } else {
            System.out.println( this.getClass().getSimpleName() + " needs refueling");
        }
    }

    abstract public void refueling(double litres);

    private void setFuel(double fuel) {
        this.fuelQuantity = fuel;
    }

    double getFuel(){
        return this.fuelQuantity;
    }

    private void setConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    private String getDoubleValue(double v) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(v);
    }
}
