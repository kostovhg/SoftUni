package org.softuni.mostwanted.domain.view;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RacersWithCarsJSONExportDto implements Serializable, Comparable<RacersWithCarsJSONExportDto> {

    @Expose
    @SerializedName("name")
    private String racerName;

    @Expose
    private Integer age;

    @Expose
    private List<String> cars;

    public RacersWithCarsJSONExportDto() {
        this.cars = new ArrayList<>();
    }

    public String getRacerName() {
        return this.racerName;
    }

    public void setRacerName(String racerName) {
        this.racerName = racerName;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<String> getCars() {
        return this.cars;
    }

    public void setCars(List<String> cars) {
        this.cars = cars;
    }

    @Override
    public int compareTo(RacersWithCarsJSONExportDto o) {
        if(this.getCars().size() == o.getCars().size()){
            return this.getRacerName().compareTo(o.getRacerName());
        } else {
            return Integer.compare(o.getCars().size(), this.getCars().size());
        }
    }
}
