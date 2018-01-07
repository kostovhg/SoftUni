package theExpanse.models.colonists.medics;

import theExpanse.models.colonists.Colonist;

public abstract class Medic extends Colonist {

    private static final int ALL_MEDICS_BONUS = 2;
    private String sign;

    protected Medic(String id, String familyId, int talent, int age, String sign) {
        super(id, familyId, talent, age);
        this.sign = sign;
    }

    // getSign method (public or protected) is necessary for test 10 to pass.
    public String getSign() {
        return this.sign;
    }

    @Override
    protected int getClassBonus(){
        // System.out.print(ALL_MEDICS_BONUS +"(All medic bonus) + ");
        return ALL_MEDICS_BONUS;
    }
}
