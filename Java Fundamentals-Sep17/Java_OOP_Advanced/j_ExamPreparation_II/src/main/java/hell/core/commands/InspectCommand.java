package hell.core.commands;

import hell.core.BaseCommand;

public class InspectCommand extends BaseCommand {

    @Override
    public String execute() throws IllegalAccessException {
        return super.getHeroController().inspect(super.getParam().get(0));
    }
}
