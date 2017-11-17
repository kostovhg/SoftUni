package p08_MilitaryElite.entities.soldiers;

import p08_MilitaryElite.interfaces.ICommando;
import p08_MilitaryElite.interfaces.IMission;

import java.util.ArrayList;
import java.util.Collection;

public class Commando extends SpecialisedSoldier implements ICommando {

    private Collection<IMission> missions;

    public Commando(
            int id, String firstName, String lastName,
            double salary,
            String corps,
            Collection<IMission> missions) {
        super(id, firstName, lastName, salary,  corps);
        this.setMissions(missions);
    }

    private void setMissions(Collection<IMission> missionsCollection) {
        if(missionsCollection != null){
            this.missions = new ArrayList<>(missionsCollection);
            return;
        }
        this.missions = new ArrayList<>();
    }

    @Override
    public Collection<IMission> getMissions() {
        return this.missions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString()).append(System.lineSeparator());
        sb.append("Missions:").append(System.lineSeparator());
        this.getMissions().forEach(m -> sb.append("  ").append(m).append(System.lineSeparator()));
        return sb.toString();
    }
}
