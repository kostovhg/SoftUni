package entities.shampoos;

import entities.labels.BasicLabel;
import enums.Size;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value="FN")
public class FreshNuke extends BasicShampoo {

    private static final String BRAND = "Fresh Nuke";
    private static final BigDecimal PRICE= new BigDecimal(9.33);
    private static final Size SIZE = Size.BIG;
    // made of Mint, Nettle, Ammonium Chloride

    public FreshNuke(){}

    public FreshNuke(BasicLabel classicLabel){
        super(BRAND, PRICE, SIZE, classicLabel);
    }

}
