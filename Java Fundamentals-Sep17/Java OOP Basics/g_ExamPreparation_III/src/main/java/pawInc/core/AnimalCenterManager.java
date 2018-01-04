package pawInc.core;

import pawInc.contracts.IAnimal;
import pawInc.contracts.ICenter;
import pawInc.contracts.Manager;
import pawInc.contracts.Writer;
import pawInc.entities.animals.Animal;
import pawInc.entities.animals.Cat;
import pawInc.entities.animals.Dog;
import pawInc.entities.centers.AdoptionCenter;
import pawInc.entities.centers.CastrationCenter;
import pawInc.entities.centers.CleansingCenter;

import java.util.*;
import java.util.stream.Collectors;

public class AnimalCenterManager implements Manager{

    private Map<String, String> animalRegister;
    private Map<String, ICenter> adoptionCenters;
    private Map<String, ICenter> cleansingCenters;
    private List<IAnimal> adoptedAnimals;
    private List<IAnimal> cleansedAnimals;
    private Map<String, ICenter> castrationCenters;
    private List<IAnimal> castratedAnimals;
    private Writer writer;

    public AnimalCenterManager() {
        this.animalRegister = new HashMap<>();
        this.adoptionCenters = new HashMap<>();
        this.cleansingCenters = new HashMap<>();
        this.adoptedAnimals = new ArrayList<>();
        this.cleansedAnimals = new ArrayList<>();
        this.castrationCenters = new HashMap<>();
        this.castratedAnimals = new ArrayList<>();
    }

    public AnimalCenterManager(Writer writer){
        this();
        this.writer = writer;
    }

    @Override
    public void registerCleansingCenter(String name) {
        this.cleansingCenters.putIfAbsent(name, new CleansingCenter(name));
    }

    @Override
    public void registerAdoptionCenter(String name) {
        this.adoptionCenters.putIfAbsent(name, new AdoptionCenter(name));
    }

    @Override
    public void registerCastrationCenter(String name) {
        this.castrationCenters.putIfAbsent(name, new CastrationCenter(name));
    }

    @Override
    public void registerDog(String name, int age, int learnedCommands, String adoptionCenterName) {
        if (this.adoptionCenters.containsKey(adoptionCenterName)) {
            IAnimal dog = new Dog(name, age, learnedCommands);
            this.adoptionCenters.get(adoptionCenterName).register(dog);
            this.animalRegister.put(name, adoptionCenterName);
        }
    }

    @Override
    public void registerCat(String name, int age, int intelligenceCoefficient, String adoptionCenterName) {
        if (this.adoptionCenters.containsKey(adoptionCenterName)) {
            IAnimal cat = new Cat(name, age, intelligenceCoefficient);
            this.adoptionCenters.get(adoptionCenterName).register(cat);
            this.animalRegister.put(name, adoptionCenterName);
        }
    }

    @Override
    public void sendForCleansing(String adoptionCenterName, String cleansingCenterName) {
        this.adoptionCenters.get(adoptionCenterName).proceed(this.cleansingCenters.get(cleansingCenterName));
    }

    @Override
    public void sendForCastration(String adoptionCenterName, String castrationCenterName) {
        this.adoptionCenters.get(adoptionCenterName).proceed(this.castrationCenters.get(castrationCenterName));
    }

    @Override
    public void cleanse(String cleansingCenterName) {
        this.cleansingCenters.get(cleansingCenterName).proceed(null)
                .forEach(a -> {
                            this.adoptionCenters.get(this.animalRegister.get(a.getName())).register(a);
                            this.cleansedAnimals.add(a);
                        });
    }

    @Override
    public void castrate(String castrationCenterName) {
        // proceed returns list which will be iterated according animal register
        // to be transferred to their initial adoption center
        // proceed takes null, as register will be responsible to return one list of animals to different adoption centers
        this.castrationCenters.get(castrationCenterName).proceed(null)
                .forEach(a -> {
                    this.adoptionCenters.get(this.animalRegister.get(a.getName())).register(a);
                    this.castratedAnimals.add(a);
                });
    }

    @Override
    public void adopt(String adoptionCenterName) {
        this.adoptedAnimals.addAll(this.adoptionCenters.get(adoptionCenterName).proceed(null));
    }

    @Override
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
                                        .map(IAnimal::getName).sorted().collect(Collectors.toList()))))
                .append(System.lineSeparator())
                .append(String.format(
                        "Cleansed Animals: %s",
                        this.cleansedAnimals.size() < 1 ?
                                "None" :
                                String.join(", ", this.cleansedAnimals.stream()
                                        .map(IAnimal::getName).sorted().collect(Collectors.toList()))))
                .append(System.lineSeparator())
                .append(String.format("Animals Awaiting Adoption: %d", countOfAnimalsWaitingAdoption))
                .append(System.lineSeparator())
                .append(String.format("Animals Awaiting Cleansing: %d", countOfAnimalsWaitingCleansing))
                .append(System.lineSeparator());

        writer.printLine(sb.toString().trim());
    }

    @Override
    public void castrationStatistics() {
        StringBuilder sb = new StringBuilder("Paw Inc. Regular Castration Statistics").append(System.lineSeparator());
        sb
                .append(String.format("Castration Centers: %d", this.castrationCenters.size()))
                .append(System.lineSeparator())
                .append(String.format("Castrated Animals: %s", this.castratedAnimals.size() < 1 ? "None" : String.join(", ", this.castratedAnimals.stream().map(IAnimal::getName).sorted().collect(Collectors.toList()))));
        writer.printLine(sb.toString());
    }

    @Override
    public long countAnimals() {
        return this.adoptionCenters.values().stream()
                .flatMap(c -> c.getStoredAnimals().stream()).filter(IAnimal::isCleansed).count();
    }
}
