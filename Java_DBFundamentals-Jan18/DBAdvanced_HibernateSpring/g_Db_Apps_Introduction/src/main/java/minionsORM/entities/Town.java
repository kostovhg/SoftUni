package minionsORM.entities;

import minionsORM.interfaces.Entity;

public class Town implements Entity{

    private static int townsID = 1;
    private int id;
    private String name;
    private String information;

    public Town(String name, String information) {
        this.name = name;
        this.information = information;
        this.id = townsID++;
    }

    public int getId() {
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
}
