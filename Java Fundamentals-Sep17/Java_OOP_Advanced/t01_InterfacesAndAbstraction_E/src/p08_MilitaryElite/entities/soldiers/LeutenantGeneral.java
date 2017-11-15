package p08_MilitaryElite.entities.soldiers;

import p08_MilitaryElite.entities.Soldier;
import p08_MilitaryElite.interfaces.ILeutenantGeneral;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class LeutenantGeneral extends Private implements ILeutenantGeneral {

    Map<String, Soldier> soldiersCommanded;

    public LeutenantGeneral(String[] tokens, Map<String, Soldier> soldiers) {
        super(tokens);
        this.soldiersCommanded = new LinkedHashMap<>();
        this.setPrivates(Arrays.copyOfRange(tokens, 5, tokens.length), soldiers);
    }

    private void setPrivates(String[] ids, Map<String, Soldier> soldiers) {
        for (int i = 0; i < ids.length; i++) {
            this.soldiersCommanded.putIfAbsent(ids[i], soldiers.get(ids[i]));
        }
    }

    @Override
    public String toString(){
        return String.format("%s\nPrivates:\n  %s",
                super.toString(),
                this.getPrivates());
    }

    private String getPrivates() {
        return String.join("\n  ",
                this.soldiersCommanded.values().stream()
                .map(Soldier::toString)
                .collect(Collectors.toList()));
    }
}
