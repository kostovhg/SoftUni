package p03_WildFarm.Entities;

public class Tiger extends Felime {
    String livingRegion;

    public Tiger(String[] args) {
        super(args);
    }

    @Override
    public void eat(Food f) {
        if(f.getClass().getSimpleName()
                .equalsIgnoreCase("vegetables")){
            throw new IllegalArgumentException("Tigers");
        }
        this.foodEaten += f.quantity;
    }

    @Override
    public void makeSound() {
        System.out.println("ROAAR!!!");
    }
}
