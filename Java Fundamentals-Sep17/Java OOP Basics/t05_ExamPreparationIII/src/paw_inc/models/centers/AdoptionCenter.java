package paw_inc.models.centers;

import paw_inc.models.animals.Animal;

import java.util.List;
import java.util.stream.Collectors;

public class AdoptionCenter extends Center{

    public AdoptionCenter(String name) {
        super(name);
    }

    public void sendForCleansing(CleansingCenter center){
        List<Animal> animals = this.getAnimals().stream()
                .filter(a -> !a.isCleansed())
                .collect(Collectors.toList());

        super.removeAll(animals);
        center.registerAll(animals);
    }

    public void sendForCastration(CastrationCenter center){
        List<Animal> animals = this.getAnimals().stream()
                .filter(a -> !a.isCleansed())
                .collect(Collectors.toList());

        super.removeAll(animals);
        center.registerAll(animals);
    }

    public List<Animal> adopt(){
        List<Animal> animals = this.getAnimals().stream()
                .filter(Animal::isCleansed)
                .collect(Collectors.toList());

        super.removeAll(animals);
        return animals;
    }
}
