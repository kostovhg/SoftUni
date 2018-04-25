package car_dealer.dto.models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.math.BigDecimal;

public class SupplierModel implements Serializable {

    @Expose
    private Long id;

    @Expose
    private String name;

    @Expose
    private int partsCount;

    public SupplierModel() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPartsCount() {
        return this.partsCount;
    }

    public void setPartsCount(int partsCount) {
        this.partsCount = partsCount;
    }
}