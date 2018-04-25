package contracts;

import entities.shampoos.BasicShampoo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public interface Ingredient extends Serializable {

    int getId();

    void setId(int id);

    String getName();

    void setName(String name);

    BigDecimal getPrice();

    void setPrice(BigDecimal price);

    List<BasicShampoo> getShampoos();

    void setShampoos(List<BasicShampoo> champoos);
}
