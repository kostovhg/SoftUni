package n_InfernoInfinityRefactoring.models.impl;

import n_InfernoInfinityRefactoring.enumerations.WeaponTypes;

public class Sword extends Weapon {

    public Sword(String name){
        super(name, WeaponTypes.SWORD.getMinDamage(), WeaponTypes.SWORD.getMaxDamage(), WeaponTypes.SWORD.getSockets());
    }
}
