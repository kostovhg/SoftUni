package app_Avatar.Entities.Monuments;

import app_Avatar.Entities.Monument;

public class AirMonument extends Monument {
    private final int airAffinity;

    public AirMonument(String name, int airAffinity) {
        super(name);
        this.airAffinity = airAffinity;
    }

    @Override
    public long getMonumentAffinity() {
        return airAffinity;
    }

    @Override
    public String toString() {
        return String.format("Air Monument: %s, Air Affinity: %d",
                this.getName(),
                this.getMonumentAffinity());
    }
}
