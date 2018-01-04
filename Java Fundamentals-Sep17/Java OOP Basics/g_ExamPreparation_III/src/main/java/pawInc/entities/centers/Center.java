package pawInc.entities.centers;

import pawInc.contracts.IAnimal;
import pawInc.contracts.ICenter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class Center implements ICenter {

    private String name;
    private List<IAnimal> storedAnimals;

    protected Center(String name) {
        this.name = name;
        this.storedAnimals = new LinkedList<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<IAnimal> getStoredAnimals() {
        return Collections.unmodifiableList(this.storedAnimals);
    }

    @Override
    public void register(IAnimal animal){
        this.storedAnimals.add(animal);
    }

    @Override
    public void register(List<IAnimal> animals) {
        this.storedAnimals.addAll(animals);
    }

    protected void removeAll(List<IAnimal> animals){
        this.storedAnimals.removeAll(animals);
    }
}
