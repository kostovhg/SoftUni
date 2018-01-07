package theExpanse.models.colonists.medics;


import static theExpanse.utilities.Constants.SURGEON_BUTCHER_SIGN;
import static theExpanse.utilities.Constants.SURGEON_PRECISE_SIGN;

public class Surgeon extends Medic {

    private static final int SURGEON_MEDIC_AGE_BONUS = 2;
    private static final int SURGEON_MEDIC_SIGN_BONUS = 3;
    private static final int SURGEON_MEDIC_SIGN_PENALTY = -3;

    public Surgeon(String id, String familyId, int talent, int age, String sign) {
        super(id, familyId, talent, age, sign);
    }

    @Override
    protected int getAgeBonus() {
        int age = super.getAge();
        int ageBonus = 0;
        if (age > 25 && age < 35) {
            ageBonus = SURGEON_MEDIC_AGE_BONUS;
        }
        // System.out.println(ageBonus + "(Surgeon age bonus) ");
        return ageBonus;
    }

    @Override
    protected int getClassBonus() {
        int signBonus = 0;
        switch (super.getSign()) {
            case SURGEON_PRECISE_SIGN:
                signBonus = SURGEON_MEDIC_SIGN_BONUS;
                break;
            case SURGEON_BUTCHER_SIGN:
                signBonus = SURGEON_MEDIC_SIGN_PENALTY;
                break;
        }
        // System.out.print(signBonus + "(Surgeon class/sign bonus) + ");
        return signBonus + super.getClassBonus();
    }
}
