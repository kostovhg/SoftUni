package com.softuni.javascriptajax.domain.models.binding;

import com.softuni.javascriptajax.domain.entities.Capital;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

public class VirusEditBindingModel {

    private int id;
    @NotNull
    @Size(min = 3, max = 10, message = "Invalid name")
    private String name;
    @NotNull
    @Size(min = 5, max = 100, message = "Invalid description")
    private String description;
    @Size(max = 50, message = "Invalid side effects")
    private String sideEffects;
    @Pattern(regexp = "[c|C]orp", message = "Creator could be only Corp or corp")
    private String creator;
    private boolean deadly;
    private boolean curable;
    @NotNull
    private String mutation;
    @Range(min = 0, max = 100)
    private int turnoverRate;
    @Range(min = 1, max = 12)
    private int hoursUntilTurn;
    private String magnitude;
    private Date releasedOn;
    @Size(min=1, message = "You should select at least one capital")
    private List<Capital> capitals;

    public VirusEditBindingModel() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCreator() {
        return this.creator;
    }

    public void setCreator(String creator) {
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

    public String getMutation() {
        return this.mutation;
    }

    public void setMutation(String mutation) {
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

    public String getMagnitude() {
        return this.magnitude;
    }

    public void setMagnitude(String magnitude) {
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
