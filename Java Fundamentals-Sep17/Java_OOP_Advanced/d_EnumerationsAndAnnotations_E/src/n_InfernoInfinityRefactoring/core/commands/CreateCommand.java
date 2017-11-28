package n_InfernoInfinityRefactoring.core.commands;

import n_InfernoInfinityRefactoring.core.BaseCommand;
import n_InfernoInfinityRefactoring.factories.WeaponFactory;
import n_InfernoInfinityRefactoring.models.api.WeaponInterface;

public class CreateCommand extends BaseCommand {

    @Override
    public String execute() {
        WeaponInterface weaponInterface = null;
        switch (super.getParams()[0]) {
            case "AXE":
                weaponInterface = WeaponFactory.createAxeWeapon(super.getParams()[1]);
                break;
            case "KNIFE":
                weaponInterface = WeaponFactory.createKnifeWeapon(super.getParams()[1]);
                break;
            case "SWORD":
                weaponInterface = WeaponFactory.createSwordWeapon(super.getParams()[1]);
                break;
        }
        super.getWeaponRepository().add(weaponInterface);
        return null;
    }
}
