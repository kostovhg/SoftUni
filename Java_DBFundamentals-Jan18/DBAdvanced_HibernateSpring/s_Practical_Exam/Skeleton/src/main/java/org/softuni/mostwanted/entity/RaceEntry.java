package org.softuni.mostwanted.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "race_entries")
public class RaceEntry {

    private Integer id; // – integer number, primary identification field.
    private boolean hasFinished; // – a boolean value.
    private Double finishTime; // – integer number.
    private Car car; // – a Car entity.
    private Racer racer; // – a Racer entity.

    public RaceEntry() {
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

    @Column(name = "has_finished")
    public boolean isHasFinished() {
        return this.hasFinished;
    }

    public void setHasFinished(boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    @Column(name = "finish_time")
    public Double getFinishTime() {
        return this.finishTime;
    }

    public void setFinishTime(Double finishTime) {
        this.finishTime = finishTime;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    public Car getCar() {
        return this.car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "racer_id")
    public Racer getRacer() {
        return this.racer;
    }

    public void setRacer(Racer racer) {
        this.racer = racer;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof RaceEntry))return false;
        RaceEntry otherEntry = (RaceEntry)other;
        return this.isHasFinished() == otherEntry.isHasFinished() &&
                this.getCar().getId().equals(otherEntry.getCar().getId()) &&
                this.getRacer().getId().equals(otherEntry.getRacer().getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(car.getId(), racer.getName());
    }
}
