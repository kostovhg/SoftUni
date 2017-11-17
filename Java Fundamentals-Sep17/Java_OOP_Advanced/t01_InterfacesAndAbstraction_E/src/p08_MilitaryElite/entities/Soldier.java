package p08_MilitaryElite.entities;

import p08_MilitaryElite.interfaces.ISoldier;

public abstract class Soldier implements ISoldier {

    private int id;
    private String firstName;
    private String lastName;

    public Soldier(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString(){
        return String.format("Name: %s %s Id: %d",
                this.getFirstName(),
                this.getLastName(),
                this.getId());
    }
}
