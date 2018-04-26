package org.softuni.ruk.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;

public class EmployeeJSONImportDTO implements Serializable {

    @Expose
    @NotEmpty
    @SerializedName("full_name")
    @Pattern(regexp = "^\\w+ \\w+$")
    private String fullName;

    @Expose
    @SerializedName("salary")
    private BigDecimal salary;

    @Expose
    @SerializedName("started_on")
    private String startedOn;

    @Expose
    @NotNull
    @SerializedName(value = "branch_name")
    private String branchName;

    public EmployeeJSONImportDTO() {
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public BigDecimal getSalary() {
        return this.salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getStartedOn() {
        return this.startedOn;
    }

    public void setStartedOn(String startedOn) {
        this.startedOn = startedOn;
    }

    public String getBranchName() {
        return this.branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}
