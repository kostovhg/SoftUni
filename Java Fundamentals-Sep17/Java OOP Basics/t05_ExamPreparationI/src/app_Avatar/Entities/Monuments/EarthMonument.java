package app_Avatar.Entities.Monuments;

import app_Avatar.Entities.Monument;

public class EarthMonument extends Monument {
    private final int earthAffinity;

    public EarthMonument(String name, int earthAffinity) {
        super(name);
        this.earthAffinity = earthAffinity;
    }

    @Override
    public long getMonumentAffinity() {
        return earthAffinity;
    }

    @Override
    public String toString() {
        return String.format("Earth Monument: %s, Earth Affinity: %d",
                this.getName(),
                this.getMonumentAffinity());
    }
}
