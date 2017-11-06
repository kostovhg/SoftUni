package p03_WildFarm.Entities;

public abstract class Animal {
    public static final String UNAPPROPRIATED = " are not eating that type of food!";
    String animalName;
    String animalType;
    Double animalWeight;
    int foodEaten;

    Animal(String[] args) {
        this.animalName = args[1];
        this.animalWeight = Double.valueOf(args[2]);
        this.animalType = args[0];
    }

    public abstract void eat(Food f);

    public abstract void makeSound();
}

