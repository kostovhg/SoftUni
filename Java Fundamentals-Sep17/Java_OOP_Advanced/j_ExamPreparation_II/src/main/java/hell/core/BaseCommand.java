package hell.core;

import hell.annotation.Inject;
import hell.interfaces.Executable;
import hell.interfaces.HeroController;

import java.util.Collections;
import java.util.List;

public abstract class BaseCommand implements Executable {

    @Inject
    private List<String> param;
    @Inject
    private HeroController heroController;


    protected BaseCommand() {
    }

    protected List<String> getParam() {
        return Collections.unmodifiableList(this.param);
    }

    protected HeroController getHeroController() {
        return this.heroController;
    }
}
