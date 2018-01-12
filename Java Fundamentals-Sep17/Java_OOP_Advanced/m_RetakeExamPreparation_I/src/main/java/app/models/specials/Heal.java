package app.models.specials;

import app.contracts.Hero;
import app.contracts.Special;

public class Heal implements Special {

    @Override
    public int trigger(Hero hero) {
        hero.setHealth(hero.getHealth() + hero.getIntelligence());
        return 0;
    }
}
