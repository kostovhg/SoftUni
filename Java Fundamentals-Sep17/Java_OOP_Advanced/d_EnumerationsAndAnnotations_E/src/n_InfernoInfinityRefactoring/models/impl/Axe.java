package n_InfernoInfinityRefactoring.models.impl;

import n_InfernoInfinityRefactoring.enumerations.WeaponTypes;

public class Axe extends Weapon {

    public Axe(String name){
        super(name, WeaponTypes.AXE.getMinDamage(), WeaponTypes.AXE.getMaxDamage(), WeaponTypes.AXE.getSockets());
    }
}
