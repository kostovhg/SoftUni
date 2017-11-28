package n_InfernoInfinityRefactoring.core.commands;

import n_InfernoInfinityRefactoring.annotations.CustomAnnotation;
import n_InfernoInfinityRefactoring.core.BaseCommand;
import n_InfernoInfinityRefactoring.models.impl.Weapon;

public class ReviewersCommand extends BaseCommand {

    @Override
    public String execute() {
        CustomAnnotation annotation = Weapon.class.getAnnotation(CustomAnnotation.class);
        return String.format("Reviewers: %s", String.join(", ", annotation.reviewers()));
    }
}
