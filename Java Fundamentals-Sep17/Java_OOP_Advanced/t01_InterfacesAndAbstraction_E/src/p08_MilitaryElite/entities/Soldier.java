package p08_MilitaryElite.entities;

import p08_MilitaryElite.interfaces.ISoldier;

public abstract class Soldier implements ISoldier {

    String id;
    String firstName;
    String lastName;

    public Soldier(String[] args) {
        this.setId(args[1]);
        this.setFirstName(args[2]);
        this.setLastName(args[3]);
    }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    private String getFirstName() {
        return firstName;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private String getLastName() {
        return lastName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString(){
        return String.format("Name: %s %s Id: %s",
                this.getFirstName(),
                this.getLastName(),
                this.getId());
    }
}
