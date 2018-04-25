package org.softuni.mostwanted.domain.binding.racers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.math.BigDecimal;

public class RacersJSONImportDto implements Serializable {

    @Expose
    @NotEmpty
    private String name;

    @Expose
    private Integer age;

    @Expose
    private BigDecimal bounty;

    @Expose
    @SerializedName("homeTown")
    private String homeTown;

    public RacersJSONImportDto() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getBounty() {
        return this.bounty;
    }

    public void setBounty(BigDecimal bounty) {
        this.bounty = bounty;
    }

    public String getHomeTown() {
        return this.homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }
}
