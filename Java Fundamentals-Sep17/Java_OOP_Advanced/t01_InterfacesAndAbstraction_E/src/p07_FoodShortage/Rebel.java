package p07_FoodShortage;

public class Rebel extends Person {

    String group;

    public Rebel(String... args) {
        super(args);
        this.group = args[args.length -1];
    }

    @Override
    public void buyFood() {
        this.food += 5;
    }

    @Override
    public int getBuyedFood() {
        return this.food;
    }
}
