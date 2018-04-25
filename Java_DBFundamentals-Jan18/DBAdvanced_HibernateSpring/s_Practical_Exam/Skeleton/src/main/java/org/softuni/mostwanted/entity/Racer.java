package org.softuni.mostwanted.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "racers")
public class Racer {

    private Integer id;
    private String name; // – a string (required).
    private Integer age; // – an integer number.
    private BigDecimal bounty; // – a decimal data type.
    private Town homeTown; // – a Town entity.
    private Set<Car> cars; // – a collection of Car entity.
    private Set<RaceEntry> entries;

    public Racer() {
        this.cars = new LinkedHashSet<>();
        this.entries = new HashSet<>();
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

    @Column(name = "name", nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "age")
    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Column(name = "bounty", precision = 10, scale = 2)
    public BigDecimal getBounty() {
        return this.bounty;
    }

    public void setBounty(BigDecimal bounty) {
        this.bounty = bounty;
    }

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="home_town_id", foreignKey=@ForeignKey(name = "fk_racer_town"))
    public Town getHomeTown() {
        return this.homeTown;
    }

    public void setHomeTown(Town homeTown) {
        this.homeTown = homeTown;
    }

    @OneToMany(mappedBy = "racer")
    public Set<Car> getCars() {
        return this.cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    @OneToMany(mappedBy = "racer")
    public Set<RaceEntry> getEntries() {
        return this.entries;
    }

    public void setEntries(Set<RaceEntry> entries) {
        this.entries = entries;
    }
}
