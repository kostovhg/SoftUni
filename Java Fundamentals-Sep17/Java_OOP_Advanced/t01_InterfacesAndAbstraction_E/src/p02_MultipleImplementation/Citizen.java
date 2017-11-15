package p02_MultipleImplementation;

public class Citizen implements
        Person,
        Birthable,
        Identifiable {

    private String name;
    private int age;
    private String id;
    private String birthDate;

    Citizen(String name, int age, String id, String birthDate) {
        this.setName(name);
        this.setAge(age);
        this.setId(id);
        this.setBirthDate(birthDate);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getAge() {
        return this.age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String getBirthDate() {
        return this.birthDate;
    }

    @Override
    public String  getId() {
        return this.id;
    }

    private void setId(String id) {
        this.id = id;
    }

    private void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
