package p08_MilitaryElite.interfaces;

import java.util.Collection;

public interface ICommando extends ISPecialisedSoldier {

    Collection<IMission> getMissions();

    @Override
    String toString();
}
