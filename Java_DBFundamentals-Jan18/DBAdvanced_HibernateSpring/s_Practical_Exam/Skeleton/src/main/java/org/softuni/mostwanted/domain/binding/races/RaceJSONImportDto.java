package org.softuni.mostwanted.domain.binding.races;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.math.BigDecimal;

public class RaceJSONImportDto implements Serializable {

    @Expose
    @NotEmpty
    private String name; // – a string (required).

    @Expose
    private Integer age; // – an integer number.

    @Expose
    private BigDecimal bounty; // – a decimal data type.

    @Expose
    @SerializedName(value = "homeTown")
    private String homeTownName; // – a Town entity.

    public RaceJSONImportDto() {
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

    public String getHomeTownName() {
        return this.homeTownName;
    }

    public void setHomeTownName(String homeTownName) {
        this.homeTownName = homeTownName;
    }
}
