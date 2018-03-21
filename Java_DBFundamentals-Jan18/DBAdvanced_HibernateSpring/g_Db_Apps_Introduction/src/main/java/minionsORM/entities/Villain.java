package minionsORM.entities;

import minionsORM.interfaces.Entity;
import minionsORM.Evilness;

public class Villain implements Entity {

    private static long villainID = 1;
    private long id;
    private String name;
    private Evilness evilnessFactor;

    public Villain(String name, Evilness evilnessFactor) {
        this.name = name;
        this.evilnessFactor = evilnessFactor;
        this.id = villainID++;
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

    public Evilness getEvilnessFactor() {
        return this.evilnessFactor;
    }

    public void setEvilnessFactor(Evilness evilnessFactor) {
        this.evilnessFactor = evilnessFactor;
    }
}
