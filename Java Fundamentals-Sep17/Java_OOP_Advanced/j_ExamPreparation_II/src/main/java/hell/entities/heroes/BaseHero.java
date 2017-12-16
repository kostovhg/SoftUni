package hell.entities.heroes;

import hell.annotation.ItemCollection;
import hell.interfaces.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class BaseHero implements Hero {

    private String name;
    private long strength;
    private long agility;
    private long intelligence;
    private long hitPoints;
    private long damage;
    private Inventory inventory;

    protected BaseHero(String name, long strength, long agility, long intelligence, long hitPoints, long damage, Inventory inventory) {
        this.name = name;
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
        this.hitPoints = hitPoints;
        this.damage = damage;
        this.inventory = inventory;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public long getStrength() {
        return this.strength + this.inventory.getTotalStrengthBonus();
    }

    @Override
    public long getAgility() {
        return this.agility + this.inventory.getTotalAgilityBonus();
    }

    @Override
    public long getIntelligence() {
        return this.intelligence + this.inventory.getTotalIntelligenceBonus();
    }

    @Override
    public long getHitPoints() {
        return this.hitPoints + this.inventory.getTotalHitPointsBonus();
    }

    @Override
    public long getDamage() {
        return this.damage + this.inventory.getTotalDamageBonus();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Item> getItems() throws IllegalAccessException {
        Map<String, Item> commonItems = null;
        Field[] declaredFields = this.inventory.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(ItemCollection.class)) {
                declaredField.setAccessible(true);
                commonItems = (Map<String, Item>) declaredField.get(this.inventory);
            }
        }
        return new ArrayList(commonItems.values());
    }

    @Override
    public void addItem(Item item) {
        this.inventory.addCommonItem(item);
    }

    @Override
    public void addRecipe(Recipe recipe) {
        this.inventory.addRecipeItem(recipe);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        try {
            sb
                    .append(String.format("%s: %s", this.getClass().getSimpleName(), this.name))
                    .append(System.lineSeparator())
                    .append(String.format("###HitPoints: %d", this.getHitPoints()))
                    .append(System.lineSeparator())
                    .append(String.format("###Damage: %d", this.getDamage()))
                    .append(System.lineSeparator())
                    .append(String.format("###Strength: %d", this.getStrength()))
                    .append(System.lineSeparator())
                    .append(String.format("###Agility: %d", this.getAgility()))
                    .append(System.lineSeparator())
                    .append(String.format("###Intelligence: %d", this.getIntelligence()))
                    .append(System.lineSeparator())
                    .append(String.format("###Items: %s",
                            this.getItems().size() == 0 ?
                                    "None" :
                                    this.getItems().stream()
                                            .map(Item::getName)
                                            .collect(Collectors.joining(", "))))
                    .append(System.lineSeparator());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return sb.toString().trim();
    }
}
