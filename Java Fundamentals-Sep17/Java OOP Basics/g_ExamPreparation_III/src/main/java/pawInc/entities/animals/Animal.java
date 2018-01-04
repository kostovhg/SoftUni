package pawInc.entities.animals;

import pawInc.contracts.IAnimal;

public abstract class Animal implements IAnimal {

    private String name;
    private int age;
    private boolean cleansingStatus;
    private boolean castrationStatus;

    protected Animal(String name, int age) {
        this.name = name;
        this.age = age;
        this.cleansingStatus = false;
        this.castrationStatus = false;
    }

    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public int getAge(){
        return this.age;
    }

    @Override
    public boolean isCleansed(){
        return this.cleansingStatus;
    }

    @Override
    public boolean isCastrated(){
        return this.castrationStatus;
    }

    @Override
    public void cleanse() {
        this.cleansingStatus = true;
    }

    @Override
    public void castrate(){
        this.castrationStatus = true;
    }
}
