package app.retake.domain.dto;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.math.BigDecimal;

public class AnimalAidJSONImportDTO implements Serializable {

    @Expose
    @Length(min = 3)
    private String name;
    @Expose
    @DecimalMin("0.01")
    private BigDecimal price;

    public AnimalAidJSONImportDTO() {
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
