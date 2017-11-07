package app_Avatar.Entities.Benders;

import app_Avatar.Entities.Bender;

public class WaterBender extends Bender {
    private final double waterClarity;

    public WaterBender(String name, int power, double waterClarity) {
        super(name, power);
        this.waterClarity = waterClarity;
    }

    private double getWaterClarity() {
        return waterClarity;
    }

    @Override
    public Double getTotalPower() {
        return this.getPower() * this.waterClarity;
    }

    @Override
    public String toString() {
        return String.format("Water Bender: %s, Power: %d, Water Clarity: %.2f",
                this.getName(),
                this.getPower(),
                this.getWaterClarity());
    }
}
