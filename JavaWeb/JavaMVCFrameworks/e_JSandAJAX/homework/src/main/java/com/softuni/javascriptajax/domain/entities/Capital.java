package com.softuni.javascriptajax.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "capitals") // make this plural
public class Capital extends BaseEntity {

    @Column(name = "name", nullable = false, updatable = false)
    private String name;
    @Column(name = "latitude", nullable = false, updatable = false, columnDefinition = "DECIMAL(5,2)")
    private Double latitude;
    @Column(name = "longitude", nullable = false, updatable = false, columnDefinition = "DECIMAL(5,2)")
    private Double longitude;

    public Capital() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
