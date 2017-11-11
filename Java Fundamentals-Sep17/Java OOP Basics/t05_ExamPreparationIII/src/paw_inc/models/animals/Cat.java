package paw_inc.models.animals;

public class Cat extends Animal {

    private int intelligence;

    public Cat(String name, int age, int intelligence, String adoptionCenter) {
        super(name, age, adoptionCenter);
        this.intelligence = intelligence;
    }
}
