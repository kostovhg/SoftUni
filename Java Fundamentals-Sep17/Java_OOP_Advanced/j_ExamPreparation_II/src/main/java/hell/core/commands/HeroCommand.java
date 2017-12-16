package hell.core.commands;

import hell.core.BaseCommand;

public class HeroCommand extends BaseCommand {

    @Override
    public String execute() {
        return super.getHeroController().createHero(super.getParam().get(0), super.getParam().get(1));
    }

}
