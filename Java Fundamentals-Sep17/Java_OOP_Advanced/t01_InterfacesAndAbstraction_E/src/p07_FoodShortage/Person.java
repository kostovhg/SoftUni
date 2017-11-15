package p07_FoodShortage;

public abstract class Person implements Buyer {

    String name;
    int age;
    int food;

    void setName(String name) {
        this.name = name;
    }

    void setAge(String ageStr) {
        this.age = Integer.parseInt(ageStr);
    }

    public Person(String... args) {
        this.setName(args[0]);
        this.setAge(args[1]);
        this.food = FOOD;
    }
}
