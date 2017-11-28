package n_InfernoInfinityRefactoring.core.commands;

import n_InfernoInfinityRefactoring.core.BaseCommand;
import n_InfernoInfinityRefactoring.models.api.WeaponInterface;

import java.util.Map;

public class CompareCommand extends BaseCommand {
    @Override
    public String execute() {
        Map<String, WeaponInterface> weapons = super.getWeaponRepository().findAll();
        if(weapons.containsKey(super.getParams()[0]) && weapons.containsKey(super.getParams()[1])) {

            int compareIndex = weapons.get(super.getParams()[0]).compareTo(weapons.get(super.getParams()[1]));

            WeaponInterface weaponInterface = weapons.get(super.getParams()[0]);

            if(compareIndex < 0) {
                weaponInterface = weapons.get(super.getParams()[1]);
            }

            if(weaponInterface != null) {
                return String.format("%s (Item Level: %s)", weaponInterface, weaponInterface.getItemLevel());
            }

        }
        return null;
    }
}
