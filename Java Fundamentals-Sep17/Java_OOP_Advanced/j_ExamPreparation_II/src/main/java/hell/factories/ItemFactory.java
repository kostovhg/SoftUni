package hell.factories;

import hell.entities.items.CommonItem;
import hell.entities.items.RecipeItem;
import hell.interfaces.Item;
import hell.interfaces.Recipe;

import java.util.List;

public final class ItemFactory {

    private ItemFactory() {
    }

    public static Item createCommonItem(String name, int strengthBonus, int agilityBonus, int intelligenceBonus, int hitPointsBonus, int damageBonus) {
        return new CommonItem(name, strengthBonus, agilityBonus, intelligenceBonus, hitPointsBonus, damageBonus);
    }

    public static Recipe createRecipeItem(String name, int strengthBonus, int agilityBonus, int intelligenceBonus, int hitPointsBonus, int damageBonus, List<String> reqItems) {
        return new RecipeItem(name, strengthBonus, agilityBonus, intelligenceBonus, hitPointsBonus, damageBonus, reqItems);
    }
}
