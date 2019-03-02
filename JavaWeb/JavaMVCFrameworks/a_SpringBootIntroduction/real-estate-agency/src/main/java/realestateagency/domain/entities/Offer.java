package realestateagency.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

import static realestateagency.utils.Constants.DEFAULT_DECIMAL_ZERO_DEFINITION;

@Entity
@Table(name = "offers")
public class Offer extends BaseEntity {

    private BigDecimal apartmentRent;
    private String apartmentType;
    private BigDecimal agencyCommission;

    public Offer() {
    }

    @Column(name = "apartment_rent", nullable = false, columnDefinition = DEFAULT_DECIMAL_ZERO_DEFINITION)
    public BigDecimal getApartmentRent() {
        return apartmentRent;
    }

    public void setApartmentRent(BigDecimal apartmentRent) {
        this.apartmentRent = apartmentRent;
    }

    @Column(name = "apartment_type", nullable = false)
    public String getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(String apartmentType) {
        this.apartmentType = apartmentType;
    }

    @Column(name = "agency_commission", nullable = false, columnDefinition = DEFAULT_DECIMAL_ZERO_DEFINITION)
    public BigDecimal getAgencyCommission() {
        return agencyCommission;
    }

    public void setAgencyCommission(BigDecimal agencyCommission) {
        this.agencyCommission = agencyCommission;
    }
}
