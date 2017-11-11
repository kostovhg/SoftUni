package paw_inc.models.centers;

import paw_inc.models.animals.Animal;

import java.util.ArrayList;
import java.util.List;

public class CastrationCenter extends Center {

    public CastrationCenter(String name) {
        super(name);
    }

    public List<Animal> castrate(){
        List<Animal> animals = new ArrayList<>(super.getAnimals());
        super.removeAll(animals);
        return animals;
    }
}
