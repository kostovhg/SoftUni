package org.softuni.mostwanted.domain.binding.towns;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

public class TownJSONImportDto implements Serializable {

    @Expose
    @NotEmpty
    private String name;

    public TownJSONImportDto() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
