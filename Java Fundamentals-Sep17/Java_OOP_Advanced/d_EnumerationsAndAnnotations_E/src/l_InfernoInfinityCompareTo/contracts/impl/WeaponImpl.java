package l_InfernoInfinityCompareTo.contracts.impl;

import l_InfernoInfinityCompareTo.contracts.api.Weapon;
import l_InfernoInfinityCompareTo.enumerations.GemType;
import l_InfernoInfinityCompareTo.enumerations.WeaponType;

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
        this.minDamage = new int[] {this.type.getMinDamage(), 0};
        this.maxDamage = new int[] {this.type.getMaxDamage(), 0};
        this.bonusStrength = 0;
        this.bonusAgility = 0;
        this.bonusVitality = 0;
        this.gems = new GemType[this.type.getSockets()];
    }

    public String getName() {
        return this.name;
    }

    private void setBonuses() {
        this.bonusVitality = 0;
        this.bonusAgility = 0;
        this.bonusStrength = 0;
        this.minDamage[1] = this.minDamage[0];
        this.maxDamage[1] = this.maxDamage[0];
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
        return String.format("%s: %d-%d Damage, +%d Strength, +%d Agility, +%d Vitality",
                this.name, this.minDamage[1], this.maxDamage[1], this.bonusStrength, this.bonusAgility, this.bonusVitality);
    }

    @Override
    public void addGem(GemType gem, int index) {
        if (index < 0 || index >= this.gems.length) {
            return;
        }
        this.gems[index] = gem;
        this.setBonuses();
    }

    @Override
    public void removeGem(int index) {
        if (index < 0 || index >= this.gems.length || this.gems[index] == null) {
            return;
        }
        this.gems[index] = null;
        this.setBonuses();
    }

    @Override
    public double getItemLevel() {
        return ((this.maxDamage[1] + this.minDamage[1]) / 2.0) + this.bonusStrength + this.bonusAgility + this.bonusVitality;
    }

    @Override
    public int compareTo(Weapon o) {
        return Double.compare(this.getItemLevel(), o.getItemLevel());
    }
}
