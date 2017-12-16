package hell.core.commands;

import hell.core.BaseCommand;

import java.util.stream.Collectors;

public class RecipeCommand extends BaseCommand {

    @Override
    public String execute() {
        return super.getHeroController().createRecipe(
                super.getParam().get(0),
                super.getParam().get(1),
                Integer.parseInt(super.getParam().get(2)),
                Integer.parseInt(super.getParam().get(3)),
                Integer.parseInt(super.getParam().get(4)),
                Integer.parseInt(super.getParam().get(5)),
                Integer.parseInt(super.getParam().get(6)),
                super.getParam().stream().skip(7).collect(Collectors.toList()));
    }
}
