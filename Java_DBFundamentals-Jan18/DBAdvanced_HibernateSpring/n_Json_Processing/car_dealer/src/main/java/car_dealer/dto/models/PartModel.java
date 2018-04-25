package car_dealer.dto.models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.math.BigDecimal;

public class PartModel implements Serializable {

    @Expose
    private String name;

    @Expose
    private BigDecimal price;

    public PartModel() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
