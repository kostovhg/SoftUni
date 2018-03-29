package models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "continents")
public class Continent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "name")
    private String name;


    @ManyToMany(mappedBy = "continent")
    private Set<Country> countries;
}