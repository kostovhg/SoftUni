package app.model.ingredients;

import app.model.shampoos.BasicShampoo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public interface Ingredient extends Serializable {

    String getName();

    void setName(String name);

    long getId();

    void setId(long id);

    BigDecimal getPrice();

    void setPrice(BigDecimal price);

    List<BasicShampoo> getShampoos();

    void setShampoos(List<BasicShampoo> shampoos);
}


