package theExpanse.models.colonists.others;

import theExpanse.models.colonists.Colonist;

public class Soldier extends Colonist {

    private final static int SOLDIER_BONUS = 3;
    private final static int SOLDIER_AGE_BONUS = 3;

    public Soldier(String id, String familyId, int talent, int age) {
        super(id, familyId, talent, age);
    }

    @Override
    protected int getClassBonus() {
        // System.out.print(SOLDIER_BONUS + "(Soldier ClassBonus) + ");
        return SOLDIER_BONUS;
    }

    @Override
    protected int getAgeBonus() {
        // System.out.println(SOLDIER_AGE_BONUS + "(Soldier AgeBonus)");
        return SOLDIER_AGE_BONUS;
    }
}
