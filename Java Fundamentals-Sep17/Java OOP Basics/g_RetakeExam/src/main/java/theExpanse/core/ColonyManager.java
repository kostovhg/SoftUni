package theExpanse.core;

import theExpanse.factories.ColonistFactory;
import theExpanse.models.Colony;

import java.util.List;

import static theExpanse.utilities.Constants.*;

public class ColonyManager {

    private Colony theColony;

    public ColonyManager(Colony colony) {
        this.theColony = colony;
    }

    public void create(List<String> params) {
        this.theColony.addColonist(ColonistFactory.create(params));
    }

    public void remove(List<String> params) {
        switch (params.get(0)){
            case REMOVE_COLONIST_PARAMETER:
                this.theColony.removeColonist(params.get(1), params.get(2));
                break;
            case REMOVE_FAMILY_PARAMETER:
                this.theColony.removeFamily(params.get(1));
                break;
        }
    }

    public void grow(List<String> params) {
            this.theColony.grow(Integer.parseInt(params.get(0)));
    }

    public String getPotential(List<String> params) {
        return String.format(COLONY_POTENTIAL_FORMAT, this.theColony.getPotential());
    }

    public String getCapacity(List<String> params) {
        return this.theColony.getCapacity();
    }

    public String getFamilyInfo(List<String> params) {
        return this.theColony.getFamilyStatistics(params.get(0));
    }
}
