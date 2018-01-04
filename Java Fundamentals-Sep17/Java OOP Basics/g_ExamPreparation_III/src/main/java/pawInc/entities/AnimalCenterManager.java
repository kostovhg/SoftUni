package pawInc.entities;

import pawInc.entities.animals.Animal;
import pawInc.entities.animals.Cat;
import pawInc.entities.animals.Dog;
import pawInc.entities.centers.AdoptionCenter;
import pawInc.entities.centers.CastrationCenter;
import pawInc.entities.centers.CleansingCenter;

import java.util.*;
import java.util.stream.Collectors;

public class AnimalCenterManager {

    private Map<String, String> animalsByAdoptionCenter;
    private Map<String, AdoptionCenter> adoptionCenters;
    private Map<String, CleansingCenter> cleansingCenters;
    private List<Animal> adoptedAnimals;
    private List<Animal> cleansedAnimals;
    private Map<String, CastrationCenter> castrationCenters;
    private List<Animal> castratedAnimals;

    public AnimalCenterManager() {
        this.animalsByAdoptionCenter = new HashMap<>();
        this.adoptionCenters = new LinkedHashMap<>();
        this.cleansingCenters = new LinkedHashMap<>();
        this.adoptedAnimals = new ArrayList<>();
        this.cleansedAnimals = new ArrayList<>();
        this.castrationCenters = new HashMap<>();
        this.castratedAnimals = new ArrayList<>();
    }

    public void registerCleansingCenter(String name) {
        this.cleansingCenters.putIfAbsent(name, new CleansingCenter(name));
    }

    public void registerAdoptionCenter(String name) {
        this.adoptionCenters.putIfAbsent(name, new AdoptionCenter(name));
    }

    public void registerCastrationCenter(String name) {
        this.castrationCenters.putIfAbsent(name, new CastrationCenter(name));
    }

    public void registerDog(String name, int age, int learnedCommands, String adoptionCenterName) {
        if (this.adoptionCenters.containsKey(adoptionCenterName)) {
            Dog dog = new Dog(name, age, learnedCommands);
            this.adoptionCenters.get(adoptionCenterName).register(dog);
            this.animalsByAdoptionCenter.put(name, adoptionCenterName);
        }
    }

    public void registerCat(String name, int age, int intelligenceCoefficient, String adoptionCenterName) {
        if (this.adoptionCenters.containsKey(adoptionCenterName)) {
            Cat cat = new Cat(name, age, intelligenceCoefficient);
            this.adoptionCenters.get(adoptionCenterName).register(cat);
            this.animalsByAdoptionCenter.put(name, adoptionCenterName);
        }
    }

    public void sendForCleansing(String adoptionCenterName, String cleansingCenterName) {
        this.adoptionCenters.get(adoptionCenterName).sendForCleanse(this.cleansingCenters.get(cleansingCenterName));
    }

    public void sendForCastration(String adoptionCenterName, String castrationCenterName) {
        this.adoptionCenters.get(adoptionCenterName).sendForCastration(this.castrationCenters.get(castrationCenterName));
    }

    public void cleanse(String cleansingCenterName) {
        this.cleansingCenters.get(cleansingCenterName).cleansing()
                .forEach(a -> {
                            this.adoptionCenters.get(this.animalsByAdoptionCenter.get(a.getName())).register(a);
                            this.cleansedAnimals.add(a);
                        });
    }

    public void castrate(String castrationCenterName) {
        this.castrationCenters.get(castrationCenterName).castrate()
                .forEach(a -> {
                    this.adoptionCenters.get(this.animalsByAdoptionCenter.get(a.getName())).register(a);
                    this.castratedAnimals.add(a);
                });
    }

    public void adopt(String adoptionCenterName) {
        this.adoptedAnimals.addAll(this.adoptionCenters.get(adoptionCenterName).adopt());
    }

    public void printStatistics() {
        long countOfAnimalsWaitingAdoption = this.countAnimals();
        long countOfAnimalsWaitingCleansing = this.cleansingCenters.values().stream().mapToLong(c -> c.getStoredAnimals().size()).sum();
        StringBuilder sb = new StringBuilder("Paw Incorporative Regular Statistics").append(System.lineSeparator());
        sb
                .append(String.format("Adoption Centers: %d", this.adoptionCenters.size()))
                .append(System.lineSeparator())
                .append(String.format("Cleansing Centers: %d", this.cleansingCenters.size()))
                .append(System.lineSeparator())
                .append(String.format(
                        "Adopted Animals: %s",
                        this.adoptedAnimals.size() < 1 ?
                                "None" :
                                String.join(", ", this.adoptedAnimals.stream()
                                        .map(Animal::getName).sorted().collect(Collectors.toList()))))
                .append(System.lineSeparator())
                .append(String.format(
                        "Cleansed Animals: %s",
                        this.cleansedAnimals.size() < 1 ?
                                "None" :
                                String.join(", ", this.cleansedAnimals.stream()
                                        .map(Animal::getName).sorted().collect(Collectors.toList()))))
                .append(System.lineSeparator())
                .append(String.format("Animals Awaiting Adoption: %d", countOfAnimalsWaitingAdoption))
                .append(System.lineSeparator())
                .append(String.format("Animals Awaiting Cleansing: %d", countOfAnimalsWaitingCleansing))
                .append(System.lineSeparator());

        System.out.println(sb.toString().trim());
    }

    public void castrationStatistics() {
        StringBuilder sb = new StringBuilder("Paw Inc. Regular Castration Statistics").append(System.lineSeparator());
        sb
                .append(String.format("Castration Centers: %d", this.castrationCenters.size()))
                .append(System.lineSeparator())
                .append(String.format("Castrated Animals: %s", this.castratedAnimals.size() < 1 ? "None" : String.join(", ", this.castratedAnimals.stream().map(Animal::getName).sorted().collect(Collectors.toList()))));
        System.out.println(sb.toString());
    }

    private long countAnimals() {
        return this.adoptionCenters.values().stream()
                .flatMap(c -> c.getStoredAnimals().stream()).filter(Animal::isCleansed).count();
    }
}
