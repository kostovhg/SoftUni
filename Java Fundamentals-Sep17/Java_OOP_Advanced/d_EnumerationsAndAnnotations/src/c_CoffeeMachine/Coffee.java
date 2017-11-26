package c_CoffeeMachine;

public class Coffee {

    private CoffeeType coffeeType;
    private CoffeeSize coffeeSize;

    public Coffee(CoffeeType coffeeType, CoffeeSize coffeeSize) {
        this.coffeeType = coffeeType;
        this.coffeeSize = coffeeSize;
    }

    @Override
    public String toString() {
        return this.coffeeSize + " " + this.coffeeType;
    }
}
