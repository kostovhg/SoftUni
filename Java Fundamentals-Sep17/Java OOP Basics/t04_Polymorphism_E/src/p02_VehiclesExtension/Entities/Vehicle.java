package p02_VehiclesExtension.Entities;

import java.text.DecimalFormat;

abstract public class Vehicle {
    double fuelQuantity;
    double fuelConsumption;
    double tankCapacity;

    Vehicle(double[] args) {
        this.setFuel(args[0]);
        this.setConsumption(args[1]);
        this.setTankCapacity(args[2]);
    }

    public abstract void driving(double km, String how);

    public void refueling(double litres) {
        if (litres + this.fuelQuantity > tankCapacity) {
            throw new IllegalArgumentException("Cannot fit fuel in tank");
        }
        if (litres <= 0) {
            throw new IllegalArgumentException("Fuel must be a positive number");
        }
        this.fuelQuantity += litres;
    }

    private void setFuel(double fuel) {
        this.fuelQuantity = fuel;
    }

    public double getFuel(){
        return this.fuelQuantity;
    }

    private void setConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    private void setTankCapacity(double tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    String getDoubleValue(double v) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(v);
    }
}
