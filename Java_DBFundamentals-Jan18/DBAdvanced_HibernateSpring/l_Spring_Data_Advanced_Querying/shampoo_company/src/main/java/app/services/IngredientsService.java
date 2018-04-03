package app.services;

import app.model.ingredients.BasicIngredient;

import java.math.BigDecimal;
import java.util.List;

public interface IngredientsService {
    List<BasicIngredient> findAllByNameStartingWith(String start);

    List<BasicIngredient> findAllByNameAndSortByPrice(List<String> names);

    BasicIngredient findByName(String name);

    void deleteByName(String name);

    void increaseAllPricesWithPercent(Double percent);

    void increaseAllPricesWithPercent(Double percent, List<String> names);
}
