package pawInc.entities.centers;

import pawInc.entities.animals.Animal;

import java.util.ArrayList;
import java.util.List;

public class CastrationCenter extends Center {

    public CastrationCenter(String name) {
        super(name);
    }

    public List<Animal> castrate(){
        super.getStoredAnimals().forEach(Animal::castrate);
        List<Animal> castratedAnimals = new ArrayList<>(super.getStoredAnimals());
        super.removeAll(castratedAnimals);
        return castratedAnimals;
    }
}
