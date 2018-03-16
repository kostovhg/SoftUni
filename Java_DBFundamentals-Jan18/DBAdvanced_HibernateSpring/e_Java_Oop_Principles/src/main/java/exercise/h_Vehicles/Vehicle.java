package exercise.h_Vehicles;

public abstract class Vehicle {

    private double fuelQuantity;
    private double fuelConsumption; // liters per km

    public Vehicle(double fuelQuantity, double fuelConsumption) {
        this.setFuelQuantity(fuelQuantity);
        this.setFuelConsumption(fuelConsumption);
    }

    protected void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    protected void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public double getFuelQuantity() {
        return this.fuelQuantity;
    }

    public double getFuelConsumption() {
        return this.fuelConsumption;
    }

    public String drive(double distance) {
        double neededFuel = distance * this.fuelConsumption;
        if (neededFuel > this.fuelQuantity) {
            return "needs refueling";
        } else {
            this.fuelQuantity -= neededFuel;

            if (distance % 1.0 != 0)
                return String.format("travelled %s km", distance);
            else
                return String.format("travelled %.0f km", distance);

        }
    }

    public void refuel(double amount) {
        this.fuelQuantity += amount;
    }
}
