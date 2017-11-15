package p08_MilitaryElite.entities.soldiers;

import p08_MilitaryElite.entities.Corps;
import p08_MilitaryElite.interfaces.ISPecialisedSoldier;

import static p08_MilitaryElite.entities.Corps.AIRFORCE;
import static p08_MilitaryElite.entities.Corps.MARINES;

public abstract class SpecialisedSoldier extends Private implements ISPecialisedSoldier {

    Corps corps;

    public SpecialisedSoldier(String[] args) {
        super(args);
        this.setCorps(args[5]);
    }

    private void setCorps(String corps) {
        switch (corps.toLowerCase()) {
            case "airforces":
                this.corps = AIRFORCE;
                break;
            case "marines":
                this.corps = MARINES;
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    private Corps getCorps() {
        return this.corps;
    }

    @Override
    public String toString() {
        return String.format("%s\nCorps: %s",
                super.toString(),
                this.getCorps());
    }

}
