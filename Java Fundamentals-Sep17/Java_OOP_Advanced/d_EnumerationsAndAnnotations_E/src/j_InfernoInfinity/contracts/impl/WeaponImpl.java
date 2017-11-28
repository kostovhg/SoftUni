package j_InfernoInfinity.contracts.impl;

import j_InfernoInfinity.contracts.api.Weapon;
import j_InfernoInfinity.enumerations.GemType;
import j_InfernoInfinity.enumerations.WeaponType;

import java.util.Arrays;
import java.util.Objects;

public class WeaponImpl implements Weapon {

    private String name;
    private WeaponType type;
    private int[] minDamage;
    private int[] maxDamage;
    private GemType[] gems;
    private int bonusStrength;
    private int bonusAgility;
    private int bonusVitality;

    public WeaponImpl(WeaponType type, String name) {
        this.name = name;
        this.type = type;
        this.minDamage = new int[]{this.type.getMinDamage(), 0};
        this.maxDamage = new int[]{this.type.getMaxDamage(), 0};
        this.bonusStrength = 0;
        this.bonusAgility = 0;
        this.bonusVitality = 0;
        this.gems = new GemType[this.type.getSockets()];
    }

    public String getName() {
        return this.name;
    }

    public WeaponType getType() {
        return this.type;
    }

    private void setBonuses() {
        this.minDamage[1] = this.minDamage[0];
        this.maxDamage[1] = this.maxDamage[0];
        this.bonusStrength = 0;
        this.bonusAgility = 0;
        this.bonusVitality = 0;
        Arrays.stream(this.gems).filter(Objects::nonNull).forEach(gem -> {
            this.bonusStrength += gem.getStrength();
            this.bonusAgility += gem.getAgility();
            this.bonusVitality += gem.getVitality();
        });
        this.minDamage[1] += this.bonusStrength * 2 + this.bonusAgility;
        this.maxDamage[1] += this.bonusStrength * 3 + this.bonusAgility * 4;
    }

    @Override
    public String toString() {
        this.setBonuses();
        return String.format("%s: %d-%d Damage, +%d Strength, +%d Agility, +%d Vitality",
                this.name, this.minDamage[1], this.maxDamage[1],
                this.bonusStrength, this.bonusAgility, this.bonusVitality);
    }

    @Override
    public void addGem(GemType gem, int index) {
        if (index < 0 || index >= this.gems.length) {
            return;
        }
        this.gems[index] = gem;
    }

    @Override
    public void removeGem(int index) {
        if (index < 0 || index >= this.gems.length || this.gems[index] == null) {
            return;
        }
        this.gems[index] = null;
    }
}
