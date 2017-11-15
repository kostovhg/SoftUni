package p08_MilitaryElite.entities.soldiers;

import p08_MilitaryElite.entities.Mission;
import p08_MilitaryElite.interfaces.ICommando;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Commando extends SpecialisedSoldier implements ICommando {

    Set<Mission> missions;

    public Commando(String[] args) {
        super(args);
        this.missions = new LinkedHashSet<>();
        this.setMissions(Arrays.copyOfRange(args, 6, args.length));
    }

    private void setMissions(String[] missions) {
        for (int i = 0; i < missions.length - 1 ; i += 2) {
            String progres = missions[i + 1];
            switch (missions[i + 1].toLowerCase()) {
                case "inprogress":
                    this.missions.add(new Mission(missions[i], missions[i + 1]));
                    break;
                case "finished":
                    this.missions.add(new Mission(missions[i], missions[i + 1]));
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public String toString() {
        return String.format("%s" +
                        "\nMissions:%s",
                super.toString(),
                this.getMissions());
    }

    private String getMissions() {
        return String.join("\n  ", this.missions.stream()
                .map(Mission::toString)
                .collect(Collectors.toList()));
    }
}
