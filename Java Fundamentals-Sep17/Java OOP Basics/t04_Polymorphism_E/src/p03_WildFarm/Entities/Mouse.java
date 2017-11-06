package p03_WildFarm.Entities;

public class Mouse extends Mammal {

    public Mouse(String[] args) {
        super(args);
    }

    @Override
    public void eat(Food f) {
        if(!f.getClass().getSimpleName()
                .equalsIgnoreCase("vegetables")){
            throw new IllegalArgumentException("Mice");
        }
        this.foodEaten += f.quantity;
    }

    @Override
    public void makeSound() {
        System.out.println("SQUEEEAAAK!");
    }

}
