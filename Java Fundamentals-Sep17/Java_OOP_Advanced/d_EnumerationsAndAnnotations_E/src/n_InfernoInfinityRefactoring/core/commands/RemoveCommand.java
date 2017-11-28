package n_InfernoInfinityRefactoring.core.commands;

import n_InfernoInfinityRefactoring.core.BaseCommand;
import n_InfernoInfinityRefactoring.models.api.WeaponInterface;

import java.util.Map;

public class RemoveCommand extends BaseCommand {

    @Override
    public String execute() {
        Map<String, WeaponInterface> weapons = super.getWeaponRepository().findAll();
        if(weapons.containsKey(super.getParams()[0])) {
            weapons.get(super.getParams()[0]).removeGem(Integer.parseInt(super.getParams()[1]));
        }
        return null;
    }
}
