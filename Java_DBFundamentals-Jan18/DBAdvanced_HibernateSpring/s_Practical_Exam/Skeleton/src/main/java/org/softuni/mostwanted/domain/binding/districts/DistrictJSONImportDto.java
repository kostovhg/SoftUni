package org.softuni.mostwanted.domain.binding.districts;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

public class DistrictJSONImportDto implements Serializable {

    @Expose
    @NotEmpty
    private String name;

    @Expose
    private String townName;

    public DistrictJSONImportDto() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTownName() {
        return this.townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }
}
