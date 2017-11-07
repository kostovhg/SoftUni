package app_Avatar.Entities.Benders;

import app_Avatar.Entities.Bender;

public class AirBender extends Bender {
    private final double aerialIntegrity;

    public AirBender(String name, int power, double aerialIntegrity) {
        super(name, power);
        this.aerialIntegrity = aerialIntegrity;
    }

    private double getAerialIntegrity() {
        return aerialIntegrity;
    }

    @Override
    public Double getTotalPower() {
        return super.getPower() * this.aerialIntegrity;
    }

    @Override
    public String toString() {
        return String.format("Air Bender: %s, Power: %d, Aerial Integrity: %.2f",
                this.getName(),
                this.getPower(),
                this.getAerialIntegrity());
    }
}