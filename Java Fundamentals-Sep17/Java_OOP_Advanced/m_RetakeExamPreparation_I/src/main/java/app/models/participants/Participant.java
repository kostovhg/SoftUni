package app.models.participants;

import app.contracts.Targetable;

public abstract class Participant implements Targetable {

    private String name;
    private double health;
    private double gold;
    private boolean isAlive;

    protected Participant() {
        this.isAlive = true;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setHealth(double health) {
        this.health = health;
    }

    @Override
    public double getHealth() {
        return this.health;
    }

//    protected void setGold(double gold){
//        this.gold = gold;
//    }

    @Override
    public double getGold() {
        return this.gold;
    }

    @Override
    public abstract double getDamage();

    @Override
    public void takeDamage(double damage) {
        this.health -= damage;
    }

    @Override
    public String attack(Targetable target) {
        if (!this.isAlive()) {
            return this.getName() + " is dead! Cannot attack.";
        }

        if (!target.isAlive()) {
            return target.getName() + " is dead! Cannot be attacked.";
        }

        target.takeDamage(this.getDamage());

        String result = this.getName() + " attacked!";
        if (!target.isAlive()) {
            this.levelUp();
            target.giveReward(this);
            result += String.format(" %s has been slain by %s.", target.getName(), this.getName());
        }
        return result;
    }

    @Override
    public void giveReward(Targetable targetable) {
        double reward = this.gold;
        this.gold = 0;
        targetable.receiveReward(reward);
    }

    @Override
    public void receiveReward(double reward) {
        this.gold += reward;
    }

    @Override
    public abstract void levelUp();

    @Override
    public boolean isAlive() {
        return this.health > 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("  Name: %s | Class: %s", this.getName(), this.getClass().getSimpleName()))
                .append(System.lineSeparator())
                .append(String.format("  Health: %.2f | Damage: %.2f", this.getHealth(), this.getDamage()));

        return sb.toString();
    }
}
