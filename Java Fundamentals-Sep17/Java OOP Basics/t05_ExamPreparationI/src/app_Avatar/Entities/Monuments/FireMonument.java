package app_Avatar.Entities.Monuments;

import app_Avatar.Entities.Monument;

public class FireMonument extends Monument {
    private final int fireAffinity;

    public FireMonument(String name, int fireAffinity) {
        super(name);
        this.fireAffinity = fireAffinity;
    }

    @Override
    public long getMonumentAffinity() {
        return fireAffinity;
    }

    @Override
    public String toString() {
        return String.format("Fire Monument: %s, Fire Affinity: %d",
                this.getName(),
                this.getMonumentAffinity());
    }
}
