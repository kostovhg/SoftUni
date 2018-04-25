package entities.ingredients.chemicalIngredients;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "AM")
public class AmmoniumChloride extends BasicChemicalIngredient {

    private static final String AMMONIUM_CHLORIDE = "Amonium Chloride";
    private static final BigDecimal PRICE = new BigDecimal(0.59);
    private static final String CHEMICAL_FORMULA = "NH4Cl";

    public AmmoniumChloride() {
        super(AMMONIUM_CHLORIDE, PRICE, CHEMICAL_FORMULA);
    }
}
