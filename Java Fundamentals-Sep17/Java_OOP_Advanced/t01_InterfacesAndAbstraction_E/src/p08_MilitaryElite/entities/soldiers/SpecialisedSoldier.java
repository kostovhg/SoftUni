package p08_MilitaryElite.entities.soldiers;

import p08_MilitaryElite.interfaces.ISPecialisedSoldier;

public abstract class SpecialisedSoldier extends Private implements ISPecialisedSoldier {

    private static final String AIRFORCES = "Airforces";
    private static final String MARINES = "Marines";

    private String corps;

    public SpecialisedSoldier(int id, String firstName, String lastName,
                              double salary,
                              String corps) {
        super(id, firstName, lastName, salary);
        this.setCorps(corps);
    }

    private void setCorps(String corps) {
        if(MARINES.equals(corps) || AIRFORCES.equals(corps)) {
            this.corps = corps;
        }
    }

    @Override
    public String getCorps() {
        return this.corps;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(String.format("Corps: %s", this.getCorps()));
        return sb.toString();
    }

}
