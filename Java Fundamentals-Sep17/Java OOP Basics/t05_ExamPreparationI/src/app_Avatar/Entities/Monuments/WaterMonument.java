package app_Avatar.Entities.Monuments;

import app_Avatar.Entities.Monument;

public class WaterMonument extends Monument {
    private final int waterAffinity;

    public WaterMonument(String name, int waterAffinity) {
        super(name);
        this.waterAffinity = waterAffinity;
    }

    @Override
    public long getMonumentAffinity() {
        return waterAffinity;
    }

    @Override
    public String toString() {
        return String.format("Water Monument: %s, Water Affinity: %d",
                this.getName(),
                this.getMonumentAffinity());
    }

}
