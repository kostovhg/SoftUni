package theExpanse.models.colonists;

public abstract class Colonist {

    private String id;
    private String familyId;
    private int talent;
    private int age;

    protected Colonist(String id, String familyId, int talent, int age) {
        this.setId(id);
        this.setFamilyId(familyId);
        this.setTalent(talent);
        this.setAge(age);
    }

    public String getId() {
        return this.id;
    }

    private void setId(String id) {
        this.id = id;
    }

    public String getFamilyId() {
        return this.familyId;
    }

    private void setFamilyId(String familyId) {
        this.familyId = familyId;
    }

    private int getTalent() {
        return this.talent;
    }

    private void setTalent(int talent) {
        this.talent = talent;
    }

    public int getAge() {
        return this.age;
    }

    private void setAge(int age) {
        this.age = age;
    }

    public int getPotential(){
        // System.out.print(this.id + ": " + this.talent + " (talent) + ");
        int potential = this.getTalent() + this.getClassBonus() + this.getAgeBonus();
        // System.out.println("total potential: " + potential);
        return potential;
    }

    public void grow(int years){
        this.age += years;
    }

    // Note! All getBonus methods should be protected for 9-th test!
    protected abstract int getAgeBonus();

    protected abstract int getClassBonus();
}
