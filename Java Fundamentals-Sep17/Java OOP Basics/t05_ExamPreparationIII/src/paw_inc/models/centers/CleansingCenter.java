package paw_inc.models.centers;

import paw_inc.models.animals.Animal;

import java.util.ArrayList;
import java.util.List;

public class CleansingCenter extends Center {

    public CleansingCenter(String name) {
        super(name);
    }

    public List<Animal> cleanse() {
        List<Animal> animals = new ArrayList<>();
        super.getAnimals().stream().forEach(a -> {
                    a.cleanse();
                    animals.add(a);
                });
        super.removeAll(animals);
        return animals;
    }
}
