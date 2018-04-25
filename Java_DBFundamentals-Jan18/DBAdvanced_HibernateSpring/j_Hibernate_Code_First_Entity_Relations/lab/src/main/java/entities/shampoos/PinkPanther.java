package entities.shampoos;

import entities.labels.BasicLabel;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value="PP")
public class PinkPanther extends BasicShampoo {

    private static final String BRAND = "Pink Panther";
    private static final BigDecimal PRICE= new BigDecimal(8.50);
    //private static final Size SIZE = Size.MEDIUM;
    // made of Lavender and Nettle

    public PinkPanther(){}

    public PinkPanther(BasicLabel classicLabel){
        super(BRAND, PRICE, classicLabel);
    }

}
