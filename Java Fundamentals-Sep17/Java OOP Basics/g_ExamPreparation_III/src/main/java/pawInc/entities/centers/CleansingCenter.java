package pawInc.entities.centers;

import pawInc.entities.animals.Animal;

import java.util.ArrayList;
import java.util.List;

public class CleansingCenter extends Center {

    public CleansingCenter(String name) {
        super(name);
    }

    public List<Animal> cleansing(){
        super.getStoredAnimals().forEach(Animal::cleanse);
        List<Animal> cleansedAnimals = new ArrayList<>(super.getStoredAnimals());
        super.removeAll(cleansedAnimals);
        return cleansedAnimals;
    }
}
