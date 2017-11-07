package app_Avatar.Entities.Benders;

import app_Avatar.Entities.Bender;

public class EarthBender extends Bender {
    private final double groundSaturation;

    public EarthBender(String name, int power, double groundSaturation) {
        super(name, power);
        this.groundSaturation = groundSaturation;
    }

    private double getGroundSaturation() {
        return groundSaturation;
    }

    @Override
    public Double getTotalPower() {
        return this.getPower() * this.groundSaturation;
    }

    @Override
    public String toString() {
        return String.format("Earth Bender: %s, Power: %d, Ground Saturation: %.2f",
                this.getName(),
                this.getPower(),
                this.getGroundSaturation());
    }

}
