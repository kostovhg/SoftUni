package org.softuni.mostwanted.domain.view;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TownByRacersJSONExportDto implements Serializable {

    @Expose
    private String name;

    @Expose
    @SerializedName("racers")
    private Integer racersCount;

    public TownByRacersJSONExportDto() {
    }

    public TownByRacersJSONExportDto(String name, Integer racersCount) {
        this.name = name;
        this.racersCount = racersCount;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRacersCount() {
        return this.racersCount;
    }

    public void setRacersCount(Integer racersCount) {
        this.racersCount = racersCount;
    }
}
