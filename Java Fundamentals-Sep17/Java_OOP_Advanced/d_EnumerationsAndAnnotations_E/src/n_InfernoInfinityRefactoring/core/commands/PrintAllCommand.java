package n_InfernoInfinityRefactoring.core.commands;

import n_InfernoInfinityRefactoring.core.BaseCommand;
import n_InfernoInfinityRefactoring.models.api.WeaponInterface;

import java.util.Map;

public class PrintAllCommand extends BaseCommand {
    @Override
    public String execute() {
        Map<String, WeaponInterface> weapons = super.getWeaponRepository().findAll();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, WeaponInterface> weapon :
                weapons.entrySet()) {
            sb.append(weapon.toString()).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
