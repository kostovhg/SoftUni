package app.models.specials;

import app.contracts.Hero;
import app.contracts.Special;

public class Swiftness implements Special {

    private double triggerPoint;

    public Swiftness(double initialHealth){
        this.triggerPoint = (initialHealth * 50.0 ) / 100.0;
    }

    @Override
    public int trigger(Hero hero) {
        if (this.triggerPoint < hero.getHealth()) {
           hero.getIntelligence();
        }
        return 0;
    }
}
