package p08_MilitaryElite.interfaces;

import java.util.Collection;

public interface IEngineer extends ISPecialisedSoldier {

    Collection<IRepair> getRepairs();

    @Override
    String toString();
}
