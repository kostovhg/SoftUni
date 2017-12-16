package hell.comparators;

import hell.interfaces.Hero;

import java.util.Comparator;

public class HeroQualitiesComparator implements Comparator<Hero> {

    @Override
    public int compare(Hero hero1, Hero hero2) {
        long comp1 = hero1.getStrength() + hero1.getAgility() + hero1.getIntelligence();
        long comp2 = hero2.getStrength() + hero2.getAgility() + hero2.getIntelligence();

        if (comp1 != comp2) {
            return Long.compare(comp2, comp1);
        }
        comp1 = hero1.getHitPoints() + hero1.getDamage();
        comp2 = hero2.getHitPoints() + hero2.getDamage();
        return Long.compare(comp2, comp1);
    }
}
