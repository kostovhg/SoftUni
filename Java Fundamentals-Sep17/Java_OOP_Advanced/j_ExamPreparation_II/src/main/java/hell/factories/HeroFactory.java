package hell.factories;

import hell.entities.heroes.Assassin;
import hell.entities.heroes.Barbarian;
import hell.entities.heroes.Wizard;
import hell.interfaces.Hero;
import hell.interfaces.Inventory;

public final class HeroFactory {

    private HeroFactory() {
    }

    public static Hero createBarbarian(String name, Inventory inventory) {
        return new Barbarian(name, inventory);
    }

    public static Hero createAssassin(String name, Inventory inventory) {
        return new Assassin(name, inventory);
    }

    public static Hero createWizard(String name, Inventory inventory) {
        return new Wizard(name, inventory);
    }
}
