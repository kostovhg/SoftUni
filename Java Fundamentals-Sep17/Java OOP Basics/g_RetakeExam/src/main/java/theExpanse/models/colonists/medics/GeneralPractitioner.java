package theExpanse.models.colonists.medics;


import static theExpanse.utilities.Constants.GP_CARELESS_SIGN;
import static theExpanse.utilities.Constants.GP_CARING_SIGN;

public class GeneralPractitioner extends Medic {

    private static final int GENERAL_PRACTITIONER_MEDIC_AGE_BONUS = 1;
    private static final int GENERAL_PRACTITIONER_MEDIC_SIGN_BONUS = 1;
    private static final int GENERAL_PRACTITIONER_MEDIC_SIGN_PENALTY = -2;

    public GeneralPractitioner(String id, String familyId, int talent, int age, String sign) {
        super(id, familyId, talent, age, sign);
    }

    @Override
    protected int getAgeBonus() {
        int ageBonus = 0;
        if (this.getAge() > 15) {
            ageBonus = GENERAL_PRACTITIONER_MEDIC_AGE_BONUS;
        }
        // System.out.println(ageBonus + "(GP age bonus)");
        return ageBonus;
    }

    @Override
    protected int getClassBonus() {
        int bonus = 0;
        switch (super.getSign()) {
            case GP_CARING_SIGN:
                bonus += GENERAL_PRACTITIONER_MEDIC_SIGN_BONUS;
                break;
            case GP_CARELESS_SIGN:
                bonus += GENERAL_PRACTITIONER_MEDIC_SIGN_PENALTY;
                break;
        }
        // System.out.print(bonus + "(GP class/sign bonus) + ");
        return bonus + super.getClassBonus();
    }
}
