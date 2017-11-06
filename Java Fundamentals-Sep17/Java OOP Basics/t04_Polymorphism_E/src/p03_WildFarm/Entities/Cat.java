package p03_WildFarm.Entities;

import java.text.DecimalFormat;

public class Cat extends Felime {
    String breed;

    public Cat(String[] args) {
        super(args);
        this.breed = args[4];
    }

    @Override
    public void eat(Food f) {
        this.foodEaten += f.quantity;
    }

    @Override
    public void makeSound() {
        System.out.println("Meowwww");
    }

    @Override
    public String toString() {
        return String.format("%s[%s, %s, %s, %s, %d]",
                this.animalType,
                this.animalName,
                this.breed,
                getDecimalFormat(this.animalWeight),
                this.livingRegion,
                this.foodEaten
        );
    }

    private String getDecimalFormat(double val) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(val);
    }
}
