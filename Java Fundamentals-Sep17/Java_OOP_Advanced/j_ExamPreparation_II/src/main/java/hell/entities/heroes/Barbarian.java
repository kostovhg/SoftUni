package hell.entities.heroes;


import hell.interfaces.Inventory;

public class Barbarian extends BaseHero {

    private static final int STRENGTH = 90;
    private static final int AGILITY = 25;
    private static final int INTELLIGENCE = 10;
    private static final int HIT_POINTS = 350;
    private static final int DAMAGE = 150;

    public Barbarian(String name, Inventory inventory) {
        super(name, STRENGTH, AGILITY, INTELLIGENCE, HIT_POINTS, DAMAGE, inventory);
    }
}
