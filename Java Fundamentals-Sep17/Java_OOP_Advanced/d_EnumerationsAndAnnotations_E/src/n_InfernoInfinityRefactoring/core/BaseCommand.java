package n_InfernoInfinityRefactoring.core;

import n_InfernoInfinityRefactoring.annotations.Inject;
import n_InfernoInfinityRefactoring.models.api.WeaponInterface;
import n_InfernoInfinityRefactoring.repositories.Repository;

public abstract class BaseCommand implements Executable {

    @Inject
    private String[] params;
    @Inject
    private Repository<WeaponInterface> weaponRepository;

    protected BaseCommand() {
    }

    public String[] getParams() {
        return this.params;
    }

    public Repository<WeaponInterface> getWeaponRepository() {
        return this.weaponRepository;
    }
}
