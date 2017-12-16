package hell.core.commands;

import hell.core.BaseCommand;

public class QuitCommand extends BaseCommand {

    @Override
    public String execute() {
        return super.getHeroController().quit();
    }
}
