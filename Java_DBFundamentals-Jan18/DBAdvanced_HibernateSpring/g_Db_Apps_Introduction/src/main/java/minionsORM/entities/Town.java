package minionsORM.entities;

import minionsORM.annotations.Column;
import minionsORM.annotations.Entity;
import minionsORM.annotations.Id;
import minionsORM.interfaces.EntityI;

@Entity(name = "towns")
public class Town implements EntityI {

    private static long townsID = 1;
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "information")
    private String information;

    public Town(String name, String information) {
        this.name = name;
        this.information = information;
        this.id = townsID++;
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

    public String getInformation() {
        return this.information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public static long getLastId(){
        return townsID;
    }
}
