package rpg_lab;

import rpg_lab.interfaces.RandomProvider;
import rpg_lab.interfaces.Target;
import rpg_lab.interfaces.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dummy implements Target {

    private int health;
    private int experience;
    private List<Weapon> possibleLoot;

    public Dummy(int health, int experience) {
        this.health = health;
        this.experience = experience;
        this.possibleLoot = new ArrayList<Weapon>();
        this.createListOfRandomWeaponsForLoot();
    }

    public Dummy(int health, int experience, List<Weapon> loot) {
        this.health = health;
        this.experience = experience;
        this.possibleLoot = loot;
    }

    private void createListOfRandomWeaponsForLoot() {
        Random rnd = new Random();
        int maxLoot = 3 + rnd.nextInt(7);

        for (int i = 0; i < maxLoot; i++) {
            this.possibleLoot.add(new Axe(rnd.nextInt(50), rnd.nextInt(50)));
        }
    }

    public int getHealth() {
        return this.health;
    }

    public void takeAttack(int attackPoints) {
        if (this.isDead()) {
            throw new IllegalStateException("Dummy is dead.");
        }

        this.health -= attackPoints;
    }

    public int giveExperience() {
        if (!this.isDead()) {
            throw new IllegalStateException("Target is not dead.");
        }

        return this.experience;
    }

    public boolean isDead() {
        return this.health <= 0;
    }

    public Weapon dropLoot(RandomProvider rnd){
        if (!this.isDead()) {
            throw new IllegalStateException("Target is not dead.");
        }

        int index = rnd.next(this.possibleLoot.size());
        return this.possibleLoot.get(index);
    }
}
