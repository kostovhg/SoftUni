package paw_inc.models;

import paw_inc.models.animals.Animal;
import paw_inc.models.animals.Cat;
import paw_inc.models.animals.Dog;
import paw_inc.models.centers.AdoptionCenter;
import paw_inc.models.centers.CastrationCenter;
import paw_inc.models.centers.CleansingCenter;

import java.text.Collator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnimalCenterManager {
    private Map<String, AdoptionCenter> adoptionCenters;
    private Map<String, CleansingCenter> cleansingCenters;
    private Map<String, CastrationCenter> castrationCenters;
    private List<Animal> adoptedAnimals;
    private List<Animal> castratedAnimals;
    private List<Animal> cleansedAnimals;

    public AnimalCenterManager() {
        this.adoptionCenters = new HashMap<>();
        this.cleansingCenters = new HashMap<>();
        this.castrationCenters = new HashMap<>();
        this.adoptedAnimals = new ArrayList<>();
        this.castratedAnimals = new ArrayList<>();
        this.cleansedAnimals = new ArrayList<>();
    }

    public void registerCleansingCenter(String name){
        this.cleansingCenters.put(name, new CleansingCenter(name));
    }

    public void registerAdoptionCenter(String name){
        this.adoptionCenters.put(name, new AdoptionCenter(name));
    }

    public void registerCastrationCenter(String name){
        this.castrationCenters.put(name, new CastrationCenter(name));
    }

    public void registerDog(String name, int age, int commands, String center) {
        this.adoptionCenters.get(center).register(new Dog(name, age, commands, center));
    }

    public void registerCat(String name, int age, int intelligence, String center) {
        this.adoptionCenters.get(center).register(new Cat(name, age, intelligence, center));
    }

    public void sendForCleansing(String fromCenter, String toCenter) {
        CleansingCenter center = this.cleansingCenters.get(toCenter);
        this.adoptionCenters.get(fromCenter).sendForCleansing(center);
    }

    public void sendForCastration(String fromCenter, String toCenter) {
        CastrationCenter center = this.castrationCenters.get(toCenter);
        this.adoptionCenters.get(fromCenter).sendForCastration(center);
    }

    public void cleanse(String centerName) {
        List<Animal> cleansedAnimals = this.cleansingCenters.get(centerName).cleanse();
        for (Animal animal :
                cleansedAnimals) {
            this.adoptionCenters.get(animal.getCenterName()).register(animal);
        }
        this.cleansedAnimals.addAll(cleansedAnimals);
    }

    public void castrate(String centerName) {
        List<Animal> castratedAnimals = this.castrationCenters.get(centerName).castrate();
        for (Animal animal :
                castratedAnimals) {
            this.adoptionCenters.get(animal.getCenterName()).register(animal);
        }
        this.castratedAnimals.addAll(castratedAnimals);
    }

    public void adopt(String centerName) {
        List<Animal> animals = this.adoptionCenters.get(centerName).adopt();
        this.adoptedAnimals.addAll(animals);
    }

    public void printStatistics() {
        StringBuilder builder = new StringBuilder();

        builder.append("Paw Incorporative Regular Statistics\n");
        builder.append(String.format("Adoption Centers: %d\n", this.adoptionCenters.size()));
        builder.append(String.format("Cleansing Centers: %d\n", this.cleansingCenters.size()));
        builder.append(String.format("Adopted Animals: %s\n", getSortedAnimals(this.adoptedAnimals)));
        builder.append(String.format("Cleansed Animals: %s\n", getSortedAnimals(this.cleansedAnimals)));
        builder.append(String.format("Animals Awaiting Adoption: %d\n", getAwaitingAdoptionCount()));
        builder.append(String.format("Animals Awaiting Cleansing: %d\n", getAwaitingCleansingCount()));

        System.out.println(builder.toString());
    }

    public void printCastrationStatistics() {
        StringBuilder builder = new StringBuilder();
        builder.append("Paw Inc. Regular Castration Statistics\n");
        builder.append(String.format("Castration Centers: %d\n", this.castrationCenters.size()));
        builder.append(String.format("Castrated Animals: %s", getSortedAnimals(this.castratedAnimals)));
        System.out.println(builder.toString());
    }

    private int getAwaitingAdoptionCount() {
        int count = this.adoptionCenters.values().stream()
                .flatMap(c -> c.getAnimals().stream())
                .filter(Animal::isCleansed)
                .collect(Collectors.toList())
                .size();
        return count;
    }

    private int getAwaitingCleansingCount(){
        int count = this.cleansingCenters.values().stream()
                .flatMap(c -> c.getAnimals().stream())
                .collect(Collectors.toList())
                .size();
        return count;
    }

    private String getSortedAnimals(List<Animal> animals) {
        if(animals.isEmpty()){
            return "None";
        }

        List<String> sorted = animals.stream()
                .map(a -> a.getName())
                .sorted(Collator.getInstance())
                .collect(Collectors.toList());
        return String.join(", ", sorted);
    }
}
