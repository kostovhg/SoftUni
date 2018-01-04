package pawInc.entities.centers;

import pawInc.entities.animals.Animal;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class Center {

    private String name;
    private List<Animal> storedAnimals;

    protected Center(String name) {
        this.name = name;
        this.storedAnimals = new LinkedList<>();
    }

    public String getName() {
        return this.name;
    }

    public List<Animal> getStoredAnimals() {
        return Collections.unmodifiableList(this.storedAnimals);
    }

    public void register(Animal animal){
        this.storedAnimals.add(animal);
    }

    public void register(List<Animal> animals) {
        this.storedAnimals.addAll(animals);
    }

    protected void removeAll(List<Animal> animals){
        this.storedAnimals.removeAll(animals);
    }
}
