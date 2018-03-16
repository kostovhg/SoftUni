package exercise.f_BirthdayCelebrations;

public class Citizen extends BaseBeing implements Identifiable {

    private int age;
    private String id;

    public Citizen(String[] args) {
        super(args[1], args[4]);
        this.setAge(Integer.parseInt(args[2]));
        this.setId(args[3]);
    }

    private void setAge(int age) {
        this.age = age;
    }

    private void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return this.age;
    }

    @Override
    public String getId() {
        return this.id;
    }
}
