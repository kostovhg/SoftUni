package p07_FoodShortage;

public class Citizen extends Person {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    String id;
    String birthdate;

    public Citizen(String... args) {
        super(args);
        this.setId(id);
        this.setBirthdate(birthdate);
    }

    @Override
    public void buyFood() {
        this.food += 10;
    }

    @Override
    public int getBuyedFood() {
        return this.food;
    }
}
