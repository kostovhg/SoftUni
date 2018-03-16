package exercise.e_BorderControl;

public class Citizen extends BaseUnit {

    private String name;
    private int age;

    public Citizen(String[] args) {
        super(args[2]);
        this.setName(args[0]);
        this.setAge(Integer.parseInt(args[1]));
    }

    private void setAge(int age) {
        this.age = age;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }
}
