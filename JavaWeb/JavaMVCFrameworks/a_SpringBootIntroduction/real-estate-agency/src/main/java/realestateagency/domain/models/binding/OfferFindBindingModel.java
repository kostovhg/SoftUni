package realestateagency.domain.models.binding;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class OfferFindBindingModel {

    private BigDecimal familyBudget;
    private String apartmentType;
    private String familyName;

    public OfferFindBindingModel() {
    }

    @NotNull
    @DecimalMin("0.001")
    public BigDecimal getFamilyBudget() {
        return this.familyBudget;
    }

    public void setFamilyBudget(BigDecimal familyBudget) {
        this.familyBudget = familyBudget;
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
    @NotEmpty
    public String getFamilyName() {
        return this.familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
}
