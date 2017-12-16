package hell.interfaces;

import java.util.List;

public interface HeroController {

    String createHero(String name, String type);

    String createItem(String itemName, String heroName, int strength, int agility, int intelligence, int hitPoints, int damage);

    String createRecipe(String itemName, String heroName, int strength, int agility, int intelligence, int hitPoints, int damage, List<String> requiredItems);

    String inspect(String heroName) throws IllegalAccessException;

    String quit();
}
