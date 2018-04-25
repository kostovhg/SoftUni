package org.softuni.mostwanted.entity;

import javax.persistence.*;

@Entity
@Table(name = "districts")
public class District {

    private Integer id; // – integer number, primary identification field.
    private String name; // – a string (required).
    private Town town; // – a Town entity.

    public District() {
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

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="town_id",foreignKey=@ForeignKey(name = "fk_district_town"))
    public Town getTown() {
        return this.town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
