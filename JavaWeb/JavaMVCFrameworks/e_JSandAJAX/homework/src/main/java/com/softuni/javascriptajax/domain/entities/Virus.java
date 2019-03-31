package com.softuni.javascriptajax.domain.entities;

import com.softuni.javascriptajax.domain.entities.enums.Creator;
import com.softuni.javascriptajax.domain.entities.enums.Magnitude;
import com.softuni.javascriptajax.domain.entities.enums.Mutation;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "viruses")
public class Virus extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "sideEffects")
    private String sideEffects;

    @Enumerated(EnumType.STRING)
    @Column(name = "creator")
    private Creator creator;

    @Column(name = "is_deadly", columnDefinition = "tinyint(1) DEFAULT 0")
    private boolean deadly;

    @Column(name = "is_curable", columnDefinition = "tinyint(1) DEFAULT 0")
    private boolean curable;

    @Enumerated(EnumType.STRING)
    @Column(name = "mutation", nullable = false)
    private Mutation mutation;

    @Column(name = "turnover_rate", columnDefinition = "TINYINT DEFAULT 0")
    private int turnoverRate;

    @Column(name = "hours_until_turnover", columnDefinition = "TINYINT DEFAULT 1")
    private int hoursUntilTurn;

    @Column(name = "magnitude", nullable = false)
    private Magnitude magnitude;

    @Column(name = "released_on")
    private Date releasedOn;

    @ManyToMany
    @JoinTable(
            name = "viruses_capitals",
            joinColumns = @JoinColumn(name = "virus_id"),
            inverseJoinColumns = @JoinColumn(name = "capital_id"))
    private List<Capital> capitals;

    public Virus() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSideEffects() {
        return this.sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public Creator getCreator() {
        return this.creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public boolean isDeadly() {
        return this.deadly;
    }

    public void setDeadly(boolean deadly) {
        this.deadly = deadly;
    }

    public boolean isCurable() {
        return this.curable;
    }

    public void setCurable(boolean curable) {
        this.curable = curable;
    }

    public Mutation getMutation() {
        return this.mutation;
    }

    public void setMutation(Mutation mutation) {
        this.mutation = mutation;
    }

    public int getTurnoverRate() {
        return this.turnoverRate;
    }

    public void setTurnoverRate(int turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    public int getHoursUntilTurn() {
        return this.hoursUntilTurn;
    }

    public void setHoursUntilTurn(int hoursUntilTurn) {
        this.hoursUntilTurn = hoursUntilTurn;
    }

    public Magnitude getMagnitude() {
        return this.magnitude;
    }

    public void setMagnitude(Magnitude magnitude) {
        this.magnitude = magnitude;
    }

    public Date getReleasedOn() {
        return this.releasedOn;
    }

    public void setReleasedOn(Date releasedOn) {
        this.releasedOn = releasedOn;
    }

    public List<Capital> getCapitals() {
        return this.capitals;
    }

    public void setCapitals(List<Capital> capitals) {
        this.capitals = capitals;
    }
}