package org.softuni.ruk.model.dto;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

public class BranchJSONImportDTO implements Serializable {

    @Expose
    @NotEmpty
    private String name;

    public BranchJSONImportDTO() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
