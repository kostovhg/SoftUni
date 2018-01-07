package theExpanse.models.colonists.engineers;

import theExpanse.models.colonists.Colonist;

public abstract class Engineer extends Colonist {

    private final static int ENGINEER_CLASS_BONUS = 3;
    private final static int ENGINEER_AGE_BONUS = 2;

    protected Engineer(String id, String familyId, int talent, int age) {
        super(id, familyId, talent, age);
    }

    @Override
    protected int getAgeBonus() {
        int ageBonus = 0;
        if(super.getAge() > 30) {
            ageBonus = ENGINEER_AGE_BONUS;
        }
        // System.out.println(ageBonus + "(all engineers age bonus)");
        return ageBonus;
    }

    @Override
    protected int getClassBonus() {
        // System.out.print(ENGINEER_CLASS_BONUS + "(all engineer class bonus) + ");
        return ENGINEER_CLASS_BONUS;
    }
}
