package hell.controllers;

import hell.comparators.HeroQualitiesComparator;
import hell.factories.HeroFactory;
import hell.factories.InventoryFactory;
import hell.factories.ItemFactory;
import hell.interfaces.*;

import javax.swing.*;
import java.util.List;

public class HeroControllerImpl implements HeroController {

    private Repository<Hero> heroRepository;

    public HeroControllerImpl(Repository<Hero> repository) {
        this.heroRepository = repository;
    }

    @Override
    public String createHero(String name, String type) {
        Inventory inventory = InventoryFactory.createHeroInventory();
        Hero hero = null;
        switch (type) {
            case "Barbarian":
                hero = HeroFactory.createBarbarian(name, inventory);
                break;
            case "Assassin":
                hero = HeroFactory.createAssassin(name, inventory);
                break;
            case "Wizard":
                hero = HeroFactory.createWizard(name, inventory);
                break;
        }
        this.heroRepository.add(hero);
        return String.format("Created %s - %s", type, name);
    }

    @Override
    public String createItem(String itemName, String heroName, int strength, int agility, int intelligence, int hitPoints, int damage) {
        Item commonItem = ItemFactory.createCommonItem(itemName, strength, agility, intelligence, hitPoints, damage);
        this.heroRepository.findByName(heroName).addItem(commonItem);
        return String.format("Added item - %s to Hero - %s", itemName, heroName);
    }

    @Override
    public String createRecipe(String itemName, String heroName, int strength, int agility, int intelligence, int hitPoints, int damage, List<String> requiredItems) {
        Recipe recipeItem = ItemFactory.createRecipeItem(itemName, strength, agility, intelligence, hitPoints, damage, requiredItems);
        this.heroRepository.findByName(heroName).addRecipe(recipeItem);
        return String.format("Added recipe - %s to Hero - %s", itemName, heroName);
    }

    @Override
    public String inspect(String heroName) throws IllegalAccessException {
        Hero hero = this.heroRepository.findByName(heroName);

        StringBuilder sb = new StringBuilder();
        sb
                .append(String.format("Hero: %s, Class: %s", heroName, hero.getClass().getSimpleName()))
                .append(System.lineSeparator())
                .append(String.format("HitPoints: %d, Damage: %d", hero.getHitPoints(), hero.getDamage()))
                .append(System.lineSeparator())
                .append(String.format("Strength: %d", hero.getStrength()))
                .append(System.lineSeparator())
                .append(String.format("Agility: %d", hero.getAgility()))
                .append(System.lineSeparator())
                .append(String.format("Intelligence: %d", hero.getIntelligence()))
                .append(System.lineSeparator())
                .append(String.format("Items: %s", hero.getItems().size() == 0 ? "None" : ""))
                .append(System.lineSeparator());

        for (Item item : hero.getItems()) {
            sb.append(item.toString()).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }

    @Override
    public String quit() {
        int counter = 1;
        StringBuilder sb = new StringBuilder();

        List<Hero> heroesList = this.heroRepository.findAll();
        heroesList.sort(new HeroQualitiesComparator());

        for (Hero hero : heroesList) {
            sb
                    .append(String.format("%d. %s", counter++, hero))
                    .append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

}
