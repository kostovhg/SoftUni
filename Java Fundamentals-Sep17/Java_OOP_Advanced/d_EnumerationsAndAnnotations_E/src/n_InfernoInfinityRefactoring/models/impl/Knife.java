package n_InfernoInfinityRefactoring.models.impl;

import n_InfernoInfinityRefactoring.enumerations.WeaponTypes;

public class Knife extends Weapon {

    public Knife(String name) {
        super(name, WeaponTypes.KNIFE.getMinDamage(), WeaponTypes.KNIFE.getMaxDamage(), WeaponTypes.KNIFE.getSockets());
    }
}
