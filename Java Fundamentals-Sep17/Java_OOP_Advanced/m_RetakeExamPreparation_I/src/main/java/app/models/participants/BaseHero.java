package app.models.participants;

import app.contracts.Hero;
import app.contracts.Special;
import app.models.Config;

public abstract class BaseHero extends Participant implements Hero {

    private int strength;
    private int dexterity;
    private int intelligence;
    private int level;

    private Special special;

    protected BaseHero() {
        this.level = 1;
        super.receiveReward(Config.HERO_START_GOLD);
    }

    @Override
    public void setStrength(int strength) {
        this.strength = strength;
        this.setHealth(strength * Config.HERO_HEALTH_MULTIPLIER);
    }

    @Override
    public int getStrength() {
        return this.strength;
    }

    @Override
    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    @Override
    public int getDexterity() {
        return this.dexterity;
    }

    @Override
    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    @Override
    public int getIntelligence() {
        return this.intelligence;
    }

    @Override
    public void setSpecial(Special special){
        this.special = special;
    }

    @Override
    public Special getSpecial() {
        return this.special;
    }

    @Override
    public void levelUp(){
        this.increaseStats((int)Math.pow(Config.LEVEL_UP_MULTIPLIER, this.level++));
        // health is calculated when strength of hero is changed
        // this.setHealth(this.getStrength() * Config.HERO_HEALTH_MULTIPLIER );
    }

    protected abstract void increaseStats(int i);

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString())
                .append(System.lineSeparator())
                .append(String.format("  %d STR | %d DEX | %d INT | %.2f Gold", this.getStrength(), this.getDexterity(), this.getIntelligence(), super.getGold()));

        return sb.toString();
    }
}
