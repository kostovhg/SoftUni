package b_KingsGambit.models;

import b_KingsGambit.contracts.Attackable;
import b_KingsGambit.contracts.KillableUnits;

import java.util.LinkedHashMap;
import java.util.Map;

public class King extends Unit implements Attackable {

    /**
     * a list with observers
     */
    private Map<String, KillableUnits> kingsUnits;

    public King(String name) {
        super(name);
        this.kingsUnits = new LinkedHashMap<>();
    }

    /**
     * method for attach a observer
     * @param unit - observer to be added
     */
    @Override
    public void addUnits(KillableUnits unit){
        this.kingsUnits.put(unit.getName(), unit);
    }

    /**
     * method for detach a observer
     * @param unitName - String key in map with Observer values
     */
    @Override
    public void kill(String unitName) {
        this.kingsUnits.remove(unitName);
    }

    /**
     * Respond of current object, and calling method to alert all observers
     * from the map with observers
     */
    @Override
    public void respondToAttack() {
        System.out.println("King " + this.name + " is under attack!");
        this.alertKingsUnits();
    }

    /**
     * Notify all observers to react
     */
    private void alertKingsUnits(){
        for (KillableUnits unit : kingsUnits.values()) {
            unit.update();
        }
    }
}
