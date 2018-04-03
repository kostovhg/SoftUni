package app.repositories;

import app.model.enums.Size;
import app.model.labels.BasicLabel;
import app.model.shampoos.BasicShampoo;
import app.model.ingredients.BasicIngredient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.BitSet;
import java.util.List;

@Repository
public interface BasicShampooRepository extends CrudRepository<BasicShampoo, Long> {

    List<BasicShampoo> findAllBySizeOrderById(Size size);

    List<BasicShampoo> findAllBySizeAndLabelOrderByPriceAsc(Size size, BasicLabel label);

    @Query("SELECT s FROM BasicShampoo AS s WHERE s.size = :size OR s.label.id = :id")
    List<BasicShampoo> findAllBySizeAndLabel_IdOrderByPriceAscIdAsc(@Param("size")Size size, @Param("id") Long labelId);

    List<BasicShampoo> findAllBySizeOrLabel_IdOrderByPriceAscIdAsc(Size size, Long labelId);

    List<BasicShampoo> findAllByPriceGreaterThanOrderByPriceDescIdAsc(BigDecimal price);

    List<BasicShampoo> findAll();

    List<BasicShampoo> findAllByBrandIn(List<String> brands);

    List<BasicShampoo> findAllByPriceIsLessThan(BigDecimal bigDecimal);

    // Task 7 withowt JPQL Query
    List<BasicShampoo> findAllByIngredients_NameIn(List<String> ingredients);

    // task 7 with JPQL Query
    @Query("SELECT s FROM BasicShampoo AS s JOIN s.ingredients AS i WHERE i.name IN :ingr")
    List<BasicShampoo> findAllByIngredientsNameIn(@Param("ingr") List<String> ingredients);

    // task 7 with JPQL Query
    @Query("SELECT s FROM BasicShampoo AS s WHERE s.ingredients.size < :size")
    List<BasicShampoo> findAllByIngredientsCountLessThan(@Param("size") int count);

//    @Query("SELECT sum(i.price) FROM BasicShampoo AS s JOIN s.ingredients AS i GROUP BY s.id HAVING s.brand LIKE :name")
//    BigDecimal getAllByBrAndIngredients_PriceSum(@Param("name")String name);

    @Query("SELECT sum(i.price) FROM BasicShampoo AS s JOIN s.ingredients AS i  WHERE s.brand = :brands")
    Double calculateSumOfIngredients(@Param("brands") String brands);

}

