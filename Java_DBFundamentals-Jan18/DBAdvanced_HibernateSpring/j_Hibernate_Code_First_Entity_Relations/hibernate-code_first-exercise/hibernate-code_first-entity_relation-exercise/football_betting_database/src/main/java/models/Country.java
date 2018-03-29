package models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @Column(name = "id", columnDefinition = "VARCHAR(3) UNIQUE NOT NULL")
    private String id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(name = "countries_continents",
            joinColumns =
                @JoinColumn(name = "country_id", referencedColumnName = "id"),
            inverseJoinColumns =
                @JoinColumn(name = "continent_id", referencedColumnName = "id"))
    private Set<Continent> continent;

    @OneToMany(mappedBy = "country")
    private Set<Town> towns;

}