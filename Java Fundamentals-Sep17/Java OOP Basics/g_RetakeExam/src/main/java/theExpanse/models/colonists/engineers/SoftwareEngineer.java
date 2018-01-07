package theExpanse.models.colonists.engineers;


public class SoftwareEngineer extends Engineer {

    private static final int SOFTWARE_ENGINEER_FLAT_BONUS = 2;

    public SoftwareEngineer(String id, String familyId, int talent, int age) {
        super(id, familyId, talent, age);
    }

    @Override
    protected int getAgeBonus() {
        int ageBonus = SOFTWARE_ENGINEER_FLAT_BONUS;
        // System.out.print(ageBonus + "(Software engineer flat bonus) + ");
        return super.getAgeBonus() + ageBonus;
    }

}
