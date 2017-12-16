package hell.factories;

import hell.entities.miscellaneous.HeroInventory;
import hell.interfaces.Inventory;

public final class InventoryFactory {

    private InventoryFactory() {
    }

    public static Inventory createHeroInventory() {
        return new HeroInventory();
    }

}
