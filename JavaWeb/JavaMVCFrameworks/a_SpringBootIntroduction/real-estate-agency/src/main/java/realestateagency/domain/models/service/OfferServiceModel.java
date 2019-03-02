package realestateagency.domain.models.service;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static realestateagency.utils.Constants.BIG_DECIMAL_HUNDRED;

public class OfferServiceModel {

    private String id;
    private BigDecimal apartmentRent;
    private String apartmentType;
    private BigDecimal agencyCommission;

    public OfferServiceModel() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotNull
    @DecimalMin(value = "0.001", message = "Value should be greater than 0.00")
    public BigDecimal getApartmentRent() {
        return this.apartmentRent;
    }

    public void setApartmentRent(BigDecimal apartmentRent) {
        this.apartmentRent = apartmentRent;
    }

    @NotNull
    @NotEmpty
    public String getApartmentType() {
        return this.apartmentType;
    }

    public void setApartmentType(String apartmentType) {
        this.apartmentType = apartmentType;
    }

    @NotNull
    @DecimalMin(value = "0", message = "Value should be equal or greater than 0.00")
    @DecimalMax("100")
    public BigDecimal getAgencyCommission() {
        return this.agencyCommission;
    }

    public void setAgencyCommission(BigDecimal agencyCommission) {
        this.agencyCommission = agencyCommission;
    }

    public BigDecimal totalPrice() {

        return this.agencyCommission
                .divide(BIG_DECIMAL_HUNDRED, RoundingMode.HALF_UP)
                .add(BigDecimal.ONE)
                .multiply(apartmentRent);
    }
}
