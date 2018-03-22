package minionsORM.entities;

import minionsORM.annotations.Column;
import minionsORM.annotations.Entity;
import minionsORM.annotations.Id;
import minionsORM.interfaces.EntityI;

@Entity(name = "minions")
public class Minion implements EntityI {

    private static long minionID = 1;

    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    @Column(name = "town_id")
    private long town_id;

    public Minion(){
    }

    public Minion(String name, int age, long town_id) {
        this.id = minionID++;
        this.name = name;
        this.age = age;
        this.town_id = town_id;
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

    public long getTown() {
        return this.town_id;
    }

    public void setTown(long town) {
        this.town_id = town_id;
    }

    public static long getLastId(){
        return minionID;
    }
}
