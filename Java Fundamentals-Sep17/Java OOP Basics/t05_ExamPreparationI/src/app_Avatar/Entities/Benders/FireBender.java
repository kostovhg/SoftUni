package app_Avatar.Entities.Benders;

import app_Avatar.Entities.Bender;

public class FireBender extends Bender {
    private final double heatAggression;

    public FireBender(String name, int power, double heatAggression) {
        super(name, power);
        this.heatAggression = heatAggression;
    }

    private double getHeatAggression() {
        return heatAggression;
    }

    @Override
    public Double getTotalPower() {
        return this.getPower() * this.heatAggression;
    }

    @Override
    public String toString() {
        return String.format("Fire Bender: %s, Power: %d, Heat Aggression: %.2f",
                this.getName(),
                this.getPower(),
                this.getHeatAggression());
    }
}
