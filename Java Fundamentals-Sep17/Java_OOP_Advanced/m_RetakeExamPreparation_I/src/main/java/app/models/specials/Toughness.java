package app.models.specials;

import app.contracts.Hero;
import app.contracts.Special;
import app.contracts.Targetable;

public class Toughness implements Special {

    @Override
    public int trigger(Hero targetable) {
        return 0;
    }
}
