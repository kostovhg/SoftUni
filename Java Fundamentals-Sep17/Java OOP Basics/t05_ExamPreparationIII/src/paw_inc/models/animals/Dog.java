package paw_inc.models.animals;

public class Dog extends Animal {

    private int commands;

    public Dog(String name, int age, int commands, String adoptionCenter) {
        super(name, age, adoptionCenter);
        this.commands = commands;
    }
}
