package pawInc.entities.centers;

import pawInc.entities.animals.Animal;

import java.util.List;
import java.util.stream.Collectors;

public class AdoptionCenter extends Center {

    public AdoptionCenter(String name) {
        super(name);
    }

    public void sendForCleanse(CleansingCenter cleansingCenter){
        List<Animal> animalsForCleansing = super.getStoredAnimals().stream()
                .filter(a -> !a.isCleansed()).collect(Collectors.toList());
        super.removeAll(animalsForCleansing);
        cleansingCenter.register(animalsForCleansing);
    }

    public void sendForCastration(CastrationCenter castrationCenter){
        List<Animal> animalsForCastration = super.getStoredAnimals().stream()
                .filter(a -> !a.isCleansed()).collect(Collectors.toList());
        super.removeAll(animalsForCastration);
        castrationCenter.register(animalsForCastration);
    }

    public List<Animal> adopt(){
        List<Animal> animalsForAdoption = super.getStoredAnimals().stream().filter(Animal::isCleansed).collect(Collectors.toList());
        super.removeAll(animalsForAdoption);
        return animalsForAdoption;
    }
}
