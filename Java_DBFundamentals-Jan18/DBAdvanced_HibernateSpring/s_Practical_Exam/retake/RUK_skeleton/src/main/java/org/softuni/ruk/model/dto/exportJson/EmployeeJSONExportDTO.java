package org.softuni.ruk.model.dto.exportJson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EmployeeJSONExportDTO {

    @Expose
    private String fullName;

    @Expose
    private BigDecimal sallary;

    @Expose
    private String startedOn;

    @Expose
    @SerializedName("clients")
    private List<String> clients;

    public EmployeeJSONExportDTO() {
        this.clients = new ArrayList<>();
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public BigDecimal getSallary() {
        return this.sallary;
    }

    public void setSallary(BigDecimal sallary) {
        this.sallary = sallary;
    }

    public String getStartedOn() {
        return this.startedOn;
    }

    public void setStartedOn(String startedOn) {
        this.startedOn = startedOn;
    }

    public List<String> getClients() {
        return this.clients;
    }

    public void setClients(List<String> clients) {
        this.clients = clients;
    }
}
