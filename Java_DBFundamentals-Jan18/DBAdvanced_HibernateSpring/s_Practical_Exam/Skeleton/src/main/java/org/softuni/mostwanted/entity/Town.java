package org.softuni.mostwanted.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "towns")
public class Town {

    private Integer id; //– integer number, primary identification field.
    private String name; //– a string (required).
    private Set<District> districts;
    private Set<Racer> racers;

    public Town() {
        this.racers = new HashSet<>();
        this.districts = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "town", orphanRemoval = true, cascade = CascadeType.ALL)
    public Set<District> getDistricts() {
        return this.districts;
    }

    public void setDistricts(Set<District> districts) {
        this.districts = districts;
    }

    @OneToMany(mappedBy = "homeTown")
    public Set<Racer> getRacers() {
        return this.racers;
    }

    public void setRacers(Set<Racer> racers) {
        this.racers = racers;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Town))return false;
        Town otherTown = (Town)other;
        return this.getName() == otherTown.getName();
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }
}
