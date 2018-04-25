package org.softuni.mostwanted.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "races")
public class Race {

    private Integer id; //– integer number, primary identification field.
    private int laps; //– integer number (required, default – 0)// set only default
    private District district; //– a District entity (required).
    private Set<RaceEntry> entries; //– a collection of RaceEntry entity.

    public Race() {
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

    @Column(name = "laps", nullable = false)
    public int getLaps() {
        return this.laps;
    }

    public void setLaps(int laps) {
        this.laps = laps;
    }

    @ManyToOne(optional = false, fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="district_id",foreignKey=@ForeignKey(name = "fk_race_district"))
    public District getDistrict() {
        return this.district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    @OneToMany
    @JoinColumn(name="race_id", referencedColumnName="id")
    public Set<RaceEntry> getEntries() {
        return this.entries;
    }

    public void setEntries(Set<RaceEntry> entries) {
        this.entries = entries;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Race))return false;
        Race otherEntry = (Race)other;
        return this.getDistrict().getId().equals(otherEntry.getId()) &&
                this.getLaps() == otherEntry.getLaps() &&
                this.getEntries().containsAll(((Race) other).getEntries());
    }

    @Override
    public int hashCode() {
        return Objects.hash(laps, district, entries);
    }
}
