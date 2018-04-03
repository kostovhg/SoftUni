package app.repositories;

import app.model.ingredients.BasicIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface BasicIngredientsRepository extends JpaRepository<BasicIngredient, Long> {

    List<BasicIngredient> findAllByNameIsStartingWithOrderByIdAsc(String start);

    List<BasicIngredient> findAllByNameInOrderByPriceAscIdAsc(List<String> names);
//
//    @Query("SELECT sum(i.price) " +
//            "FROM BasicIngredient AS i " +
//            "JOIN shampoos_igredients as si ON si.ingredient_id = i.id " +
//            "JOIN shampoos AS s ON si.shampoo_id = s.id " +
//            "GROUP BY s.brand " +
//            "HAVING s.brand LIKE :brand")
//    BigDecimal findAllByChampooBrandAndSumPrices(@Param("brand")String shampooBrand);

    @Query("DELETE FROM BasicIngredient AS i WHERE i.name = :name")
    @Modifying
    void deleteByName(@Param("name") String name);

    BasicIngredient findByName(String name);

    @Query("UPDATE BasicIngredient AS i SET i.price = (i.price * (1 + :percent / 100.0)) WHERE 1 = 1")
    @Modifying
    void increaseAllPricesWithPercent(@Param("percent") Double percent);

    @Query("UPDATE BasicIngredient AS i SET i.price = (i.price * (1 + :percent / 100.0)) WHERE i.name IN :names")
    @Modifying
    void increaseAllPricesWithPercent(@Param("percent") Double percent,@Param("names") List<String> names);
}
