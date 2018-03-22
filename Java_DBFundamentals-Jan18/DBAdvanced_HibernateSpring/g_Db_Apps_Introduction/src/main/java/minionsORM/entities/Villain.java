package minionsORM.entities;

import minionsORM.annotations.Column;
import minionsORM.annotations.Entity;
import minionsORM.annotations.Id;
import minionsORM.interfaces.EntityI;
import minionsORM.utilities.Evilness;

@Entity(name = "villains")
public class Villain implements EntityI {

    private static long villainID = 1;
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "evilness_factor")
    private Evilness evilnessFactor;

    public Villain(String name, Evilness evilnessFactor) {
        this.name = name;
        this.evilnessFactor = evilnessFactor;
        this.id = villainID++;
    }

    public Villain(String name){
        this(name, Evilness.EVIL);
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

    public static long getVillainId(){
        return villainID;
    }
}
