package pawInc.entities.centers;

import pawInc.contracts.IAnimal;
import pawInc.contracts.ICenter;

import java.util.ArrayList;
import java.util.List;

public class CleansingCenter extends Center {

    public CleansingCenter(String name) {
        super(name);
    }

    @Override
    public List<IAnimal> proceed(ICenter adoptionCenter){
        super.getStoredAnimals().forEach(IAnimal::cleanse);
        List<IAnimal> cleansedAnimals = new ArrayList<>(super.getStoredAnimals());
        super.removeAll(cleansedAnimals);
        return cleansedAnimals;
    }
}
