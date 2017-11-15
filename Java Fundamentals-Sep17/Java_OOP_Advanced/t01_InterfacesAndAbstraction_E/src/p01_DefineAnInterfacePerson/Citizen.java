package p01_DefineAnInterfacePerson;

public class Citizen implements Person {

    private String name;
    private int age;

    Citizen(String name, int age){
        this.setName(name);
        this.setAge(age);
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
}
