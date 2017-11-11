package paw_inc.models.animals;

public abstract class Animal {
    private String name;
    private int age;
    private boolean isCleansed;
    private String adoptionCenter;

    protected Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    protected Animal(String name, int age, String adoptionCenter) {
        this(name, age);
        this.adoptionCenter = adoptionCenter;
    }

    public String getName() {
        return name;
    }

    public boolean isCleansed() {
        return isCleansed;
    }

    public void cleanse() {
        isCleansed = true;
    }

    public String getCenterName() {
        return adoptionCenter;
    }
}
