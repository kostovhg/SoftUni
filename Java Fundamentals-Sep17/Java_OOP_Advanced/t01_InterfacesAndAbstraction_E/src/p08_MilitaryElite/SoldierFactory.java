package p08_MilitaryElite;

import p08_MilitaryElite.entities.Soldier;
import p08_MilitaryElite.entities.soldiers.*;

import java.util.Map;

class SoldierFactory {

    Map<String, Soldier> soldiersSet;

    SoldierFactory(Map<String, Soldier> soldiers) {
        this.soldiersSet = soldiers;
    }

    public Soldier createSoldier(String input) {
        String[] tokens = input.split("\\s+");
        try {
            switch (tokens[0].toLowerCase()) {
                case "private":
                    return new Private(tokens);
                case "leutenantgeneral":
                    return new LeutenantGeneral(tokens, soldiersSet);
                case "engineer":
                    return new Engineer(tokens);
                case "commando":
                    return new Commando(tokens);
                case "spy":
                    return new Spy(tokens);
                default:
                    return null;
            }
        } catch (IllegalArgumentException e){
            return null;
        }
    }
}
