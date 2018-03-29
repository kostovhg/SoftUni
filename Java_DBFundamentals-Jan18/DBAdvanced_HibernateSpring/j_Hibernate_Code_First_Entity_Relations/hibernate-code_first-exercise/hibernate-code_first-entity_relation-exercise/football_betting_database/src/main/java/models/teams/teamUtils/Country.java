package models.teams.teamUtils;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @Column(name = "id", unique = true, columnDefinition = "VARCHAR(3)")
    private String id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(name = "countries_continents",
            joinColumns =
                @JoinColumn(name = "country_id", referencedColumnName = "id"),
            inverseJoinColumns =
                @JoinColumn(name = "continent_id", referencedColumnName = "id"))
    private Set<Continent> continents;

    @OneToMany(mappedBy = "country")
    private Set<Town> towns;

    public Country() {
    }

    public Country(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Continent> getContinents() {
        return this.continents;
    }

    public Set<Town> getTowns() {
        return this.towns;
    }
}