package realestateagency.domain.models.binding;

import java.math.BigDecimal;

public class OfferRegisterBindingModel {

    private BigDecimal apartmentRent;
    private String apartmentType;
    private BigDecimal agencyCommission;

    public OfferRegisterBindingModel() {
    }

    public BigDecimal getApartmentRent() {
        return this.apartmentRent;
    }

    public void setApartmentRent(BigDecimal apartmentRent) {
        this.apartmentRent = apartmentRent;
    }

    public String getApartmentType() {
        return this.apartmentType.toLowerCase();
    }

    public void setApartmentType(String apartmentType) {
        this.apartmentType = apartmentType.toLowerCase();
    }

    public BigDecimal getAgencyCommission() {
        return this.agencyCommission;
    }

    public void setAgencyCommission(BigDecimal agencyCommission) {
        this.agencyCommission = agencyCommission;
    }
}
