package p03_WildFarm.Entities;

public class Zebra extends Mammal {

    public Zebra(String[] args) {
        super(args);
    }

    @Override
    public void eat(Food f) {
        if(!f.getClass().getSimpleName()
                .equalsIgnoreCase("vegetables")){
            throw new IllegalArgumentException("Zebras");
        }
        this.foodEaten += f.quantity;
    }

    @Override
    public void makeSound() {
        System.out.println("Zs");
    }
}
