package hell.core.commands;

import hell.core.BaseCommand;

public class ItemCommand extends BaseCommand {

    @Override
    public String execute() {
        return super.getHeroController().createItem(
                super.getParam().get(0),
                super.getParam().get(1),
                Integer.parseInt(super.getParam().get(2)),
                Integer.parseInt(super.getParam().get(3)),
                Integer.parseInt(super.getParam().get(4)),
                Integer.parseInt(super.getParam().get(5)),
                Integer.parseInt(super.getParam().get(6)));
    }
}
