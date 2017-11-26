package h_PetClinics;

public class Pet {

    private String name;
    private int age;
    private String kind;

    public Pet(String name, String age, String kind) {
        this.setName(name);
        this.setAge(age);
        this.setKind(kind);
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setAge(String age) {
        this.age = Integer.parseInt(age);
    }

    private void setKind(String kind) {
        this.kind = kind;
    }

    @Override
    public String toString() {
        return String.format("%s %d %s", this.name, this.age, this.kind);
    }
}
