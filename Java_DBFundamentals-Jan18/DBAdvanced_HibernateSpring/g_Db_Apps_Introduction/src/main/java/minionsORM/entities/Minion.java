package minionsORM.entities;

import minionsORM.interfaces.Entity;

public class Minion implements Entity{

    private static long minionID = 1;
    private long id;
    private String name;
    private int age;
    private String town;

    public Minion(String name, int age, String town) {
        this.name = name;
        this.age = age;
        this.town = town;
        this.id = minionID++;
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTown() {
        return this.town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
