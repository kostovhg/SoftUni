package pawInc.entities.animals;

public abstract class Animal {

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

    public String getName(){
        return this.name;
    }

    public int getAge(){
        return this.age;
    }

    public boolean isCleansed(){
        return this.cleansingStatus;
    }

    public boolean isCastrated(){
        return this.castrationStatus;
    }

    public void cleanse() {
        this.cleansingStatus = true;
    }

    public void castrate(){
        this.castrationStatus = true;
    }
}
