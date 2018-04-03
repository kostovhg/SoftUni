package app.services;

import app.model.enums.Size;
import app.model.labels.BasicLabel;
import app.model.shampoos.BasicShampoo;

import java.math.BigDecimal;
import java.util.List;

public interface ShampooService {
    List<BasicShampoo> getAllBySize(Size size);

    List<BasicShampoo> getAllBySizeAndLabelIdOrderedByPriceAsc(Size size, Long id);

    List<BasicShampoo> getAllBySizeAndLabelOrderedByPriceAsc(Size size, BasicLabel label);

    List<BasicShampoo> getAllBySizeOrLabel_IdOrderByPriceAsc(Size size, Long id);

    List<BasicShampoo> getAll();

    List<BasicShampoo> findAllByPriceGreaterThanOrderByPriceDesIdAsc
            (BigDecimal price);

    List<BasicShampoo> findAllByPriceLowerThan(BigDecimal price);

    List<BasicShampoo> findAllByIngredientsIn(List<String> ingr);

    List<BasicShampoo> findAllWithIngredientsCountLessThan(int size);

    List<BasicShampoo> findAllByBrands(List<String> brands);

    Double findSumOfIngredients(String brands);
}
