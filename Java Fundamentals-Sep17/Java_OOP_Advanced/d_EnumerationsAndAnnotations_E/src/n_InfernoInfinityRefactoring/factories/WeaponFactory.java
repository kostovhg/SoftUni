package n_InfernoInfinityRefactoring.factories;

import n_InfernoInfinityRefactoring.models.api.WeaponInterface;
import n_InfernoInfinityRefactoring.models.impl.Axe;
import n_InfernoInfinityRefactoring.models.impl.Knife;
import n_InfernoInfinityRefactoring.models.impl.Sword;

public class WeaponFactory {

    private WeaponFactory() {
    }

    public static WeaponInterface createAxeWeapon(String name) {
        return new Axe(name);
    }

    public static WeaponInterface createKnifeWeapon(String name) {
        return new Knife(name);
    }

    public static WeaponInterface createSwordWeapon(String name) {
        return new Sword(name);
    }

}
