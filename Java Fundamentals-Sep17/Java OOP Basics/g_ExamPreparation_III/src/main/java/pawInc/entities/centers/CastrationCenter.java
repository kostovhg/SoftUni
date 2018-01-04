package pawInc.entities.centers;

import pawInc.contracts.IAnimal;
import pawInc.contracts.ICenter;

import java.util.ArrayList;
import java.util.List;

public class CastrationCenter extends Center {

    public CastrationCenter(String name) {
        super(name);
    }

    @Override
    public List<IAnimal> proceed(ICenter adoptionCenter){
        super.getStoredAnimals().forEach(IAnimal::castrate);
        List<IAnimal> castratedAnimals = new ArrayList<>(super.getStoredAnimals());
        super.removeAll(castratedAnimals);
        return castratedAnimals;
    }
}
