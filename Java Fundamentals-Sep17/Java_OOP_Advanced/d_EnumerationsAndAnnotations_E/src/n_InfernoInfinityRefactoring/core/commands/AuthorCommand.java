package n_InfernoInfinityRefactoring.core.commands;

import n_InfernoInfinityRefactoring.annotations.CustomAnnotation;
import n_InfernoInfinityRefactoring.core.BaseCommand;
import n_InfernoInfinityRefactoring.models.impl.Weapon;

public class AuthorCommand extends BaseCommand {
    @Override
    public String execute() {
        CustomAnnotation annotation = Weapon.class.getAnnotation(CustomAnnotation.class);
        return String.format("Author: %s", annotation.author());
    }
}
