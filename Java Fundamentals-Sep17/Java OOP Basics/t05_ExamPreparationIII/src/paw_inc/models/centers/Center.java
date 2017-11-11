package paw_inc.models.centers;

import paw_inc.models.animals.Animal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Center {
    private String name;
    private List<Animal> animals;

    protected Center(String name) {
        this.name = name;
        this.animals = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public List<Animal> getAnimals() {
        return Collections.unmodifiableList(this.animals);
    }

    public void register(Animal a){
        this.animals.add(a);
    }

    public void registerAll(List<Animal> a){
        this.animals.addAll(a);
    }

    public void removeAll(List<Animal> a){
        this.animals.removeAll(a);
    }
}
