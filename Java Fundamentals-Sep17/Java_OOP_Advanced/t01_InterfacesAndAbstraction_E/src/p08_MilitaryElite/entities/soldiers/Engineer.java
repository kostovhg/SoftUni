package p08_MilitaryElite.entities.soldiers;

import p08_MilitaryElite.entities.Repair;
import p08_MilitaryElite.interfaces.IEngineer;

import java.util.*;
import java.util.stream.Collectors;

public class Engineer extends SpecialisedSoldier implements IEngineer {

    List<Repair> repairs;

    public Engineer(String[] args) {
        super(args);
        this.repairs = new ArrayList<>();
        this.setRepairs(Arrays.copyOfRange(args, 6, args.length ));
    }

    private void setRepairs(String[] repairsStr){
        for (int i = 0; i < repairsStr.length; i += 2) {
            repairs.add(new Repair(repairsStr[i], repairsStr[i + 1]));
        }
    }

    @Override
    public String toString(){
        return String.format("%s" +
                "\nRepairs:\n  %s",
                super.toString(),
                this.getRepairs());
    }

    private String getRepairs() {
        return String.join("\n  ", repairs.stream().map(Repair::toString).collect(Collectors.toList()));
    }
}
