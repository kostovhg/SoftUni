package p08_MilitaryElite.entities.soldiers;

import p08_MilitaryElite.interfaces.IEngineer;
import p08_MilitaryElite.interfaces.IPrivate;
import p08_MilitaryElite.interfaces.IRepair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Engineer extends SpecialisedSoldier implements IEngineer {

    private List<IRepair> repairs;

    public Engineer(
            int id, String firstName, String lastName,
            double salary,
            String corps,
            Collection<IRepair> repairs) {
        super(id, firstName, lastName, salary, corps);
        this.setRepairs(repairs);
    }

    private void setRepairs(Collection<IRepair> repairsCollection) {
        if (repairsCollection != null) {
            this.repairs = new ArrayList<>(repairsCollection);
            return;
        }
        this.repairs = new ArrayList<>();
    }

    @Override
    public Collection<IRepair> getRepairs() {
        return this.repairs;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString()).append(System.lineSeparator());
        sb.append("Repairs:").append(System.lineSeparator());
        this.getRepairs().forEach(r -> sb.append("  ").append(r).append(System.lineSeparator()));
        return sb.toString();
    }
}
