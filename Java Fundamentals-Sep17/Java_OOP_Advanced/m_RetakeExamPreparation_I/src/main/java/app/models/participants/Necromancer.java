package app.models.participants;

import app.models.Config;

public class Necromancer extends BaseHero {

    public Necromancer() {
        super();
        this.setStrength(Config.NECROMANCER_BASE_STRENGTH);
        this.setDexterity(Config.NECROMANCER_BASE_DEXTERITY);
        this.setIntelligence(Config.NECROMANCER_BASE_INTELLIGENCE);
    }

    @Override
    public double getDamage() {
        return (this.getIntelligence() * 2) + (this.getDexterity() * 2) + (this.getStrength() * 2);
        //(Intelligence * 2) + (Dexterity * 2) + (Strength * 2)
    }

    @Override
    protected void increaseStats(int multiplier) {
        this.setStrength(Config.NECROMANCER_BASE_STRENGTH * multiplier);
        this.setDexterity(Config.NECROMANCER_BASE_DEXTERITY * multiplier);
        this.setIntelligence(Config.NECROMANCER_BASE_INTELLIGENCE * multiplier);
    }

}
