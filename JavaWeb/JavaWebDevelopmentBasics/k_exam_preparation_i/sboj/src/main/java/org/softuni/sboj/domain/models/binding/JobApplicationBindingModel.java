package org.softuni.sboj.domain.models.binding;

import java.math.BigDecimal;

public class JobApplicationBindingModel {

    private String sector;
    private String profession;
    private BigDecimal salary;
    private String description;

    public JobApplicationBindingModel() {
    }

    public String getSector() {
        return this.sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getProfession() {
        return this.profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public BigDecimal getSalary() {
        return this.salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
