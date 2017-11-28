package n_InfernoInfinityRefactoring.models.impl;

import n_InfernoInfinityRefactoring.annotations.CustomAnnotation;
import n_InfernoInfinityRefactoring.enumerations.GemTypes;
import n_InfernoInfinityRefactoring.models.api.WeaponInterface;

import java.util.Arrays;
import java.util.stream.Collectors;

@CustomAnnotation
public abstract class Weapon<T extends Weapon> implements WeaponInterface<Weapon> {

    private String name;
    private int minDamage;
    private int maxDamage;
    private int maxSockets;
    private int bonusStrength;
    private int bonusAgility;
    private int bonusVitality;
    private GemTypes[] gems;

    protected Weapon(String name, int minDamage, int maxDamage, int maxSockets) {
        this.name = name;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.maxSockets = maxSockets;
        this.gems = new GemTypes[this.maxSockets];
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void addGem(String gemType, int index) {
        if(index < 0 || index >= this.gems.length || !Arrays.stream(GemTypes.values()).map(GemTypes::name).collect(Collectors.toList()).contains(gemType.toUpperCase())) {
            return;
        }
        if(this.gems[index] != null){
            this.removeStatus(this.gems[index]);
        }
        this.gems[index] = GemTypes.valueOf(gemType.toUpperCase());
        this.addStats(this.gems[index]);
    }

    @Override
    public void removeGem(int index) {
        if(index < 0 || index >= this.gems.length || this.gems[index] == null) {
            return;
        }
        this.removeStatus(this.gems[index]);
        this.gems[index] = null;
    }

    @Override
    public int compareTo(Weapon weapon) {
        return Double.compare(this.getItemLevel(), weapon.getItemLevel());
    }

    @Override
    public String toString() {
        return String.format("%s: %d-%d Damage, +%d Strength, +%d Agility, +%d Vitality", this.name, this.minDamage, this.maxDamage, this.bonusStrength, this.bonusAgility, this.bonusVitality);
    }

    private void addStats(GemTypes gem) {
        this.bonusStrength += gem.getStrength();
        this.bonusAgility += gem.getAgility();
        this.bonusVitality += gem.getVitality();
        this.minDamage += (gem.getStrength() * 2) + gem.getAgility();
        this.maxDamage += (gem.getStrength() * 3) + (gem.getAgility() * 4);
    }

    private void removeStatus(GemTypes gem){
        this.bonusStrength -= gem.getStrength();
        this.bonusAgility -= gem.getAgility();
        this.bonusVitality -= gem.getVitality();
        this.minDamage -= (gem.getStrength() * 2) + gem.getAgility();
        this.maxDamage -= (gem.getStrength() * 3) + (gem.getAgility() * 4);
    }

    @Override
    public double getItemLevel() {
        return ((this.minDamage + this.maxDamage) / 2.0) + this.bonusStrength + this.bonusAgility + this.bonusVitality;
    }
}
