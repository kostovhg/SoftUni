package app;

import app.model.enums.Size;
import app.model.ingredients.BasicIngredient;
import app.repositories.BasicLabelRepository;
import app.services.IngredientsService;
import app.services.ShampooService;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Application {

    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    public static final String STRING_LINE = "==============================";
    public static final Logger log = org.slf4j.LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

    @Bean
    public CommandLineRunner demo(
            BasicLabelRepository labelRepository,
            IngredientsService ingredientsService,
            ShampooService shampooService) {
        return (args) -> {

            log.info(STRING_LINE);
            log.info(" 1. Shampoo by size");
            log.info(STRING_LINE);
            shampooService.getAllBySize(Size.MEDIUM)
                    .forEach(s ->
                            log.info(String.format(
                                    "%s %s %.2flv.",
                                    s.getBrand(),
                                    s.getSize(),
                                    s.getPrice())));
            log.info("");

            log.info(STRING_LINE);
            log.info("2 .Shampoo by size or label id order by price");
            log.info(STRING_LINE);
            shampooService.getAllBySizeOrLabel_IdOrderByPriceAsc(Size.MEDIUM, 10L)
                    .forEach(s ->
                            log.info(String.format(
                                    "%s %s %.2flv.",
                                    s.getBrand(),
                                    s.getSize(),
                                    s.getPrice())));
            log.info("");

            log.info(STRING_LINE);
            log.info("3. Select Shampoos by Price");
            log.info(STRING_LINE);
            shampooService.findAllByPriceGreaterThanOrderByPriceDesIdAsc(BigDecimal.valueOf(5.0))
                    .forEach(s ->
                            log.info(String.format(
                                    "%s %s %.2flv.",
                                    s.getBrand(),
                                    s.getSize(),
                                    s.getPrice())));
            log.info("");

            log.info(STRING_LINE);
            log.info("4. Select Ingredients by Name");
            log.info(STRING_LINE);
            log.warn("Enter a letter ->");
            //ingredientsService.findAllByNameStartingWith(READER.readLine())
            ingredientsService.findAllByNameStartingWith("M")
                    .forEach(i ->
                            log.info(i.getName()));
            log.info("");

            log.info(STRING_LINE);
            log.info("5. Select Ingredients by Names");
            log.info(STRING_LINE);
            log.warn("Enter list of ingredients names on one line -> ");
            //ingredientsService.findAllByNameAndSortByPrice(Arrays.asList(READER.readLine().split("\\s+")))
            ingredientsService.findAllByNameAndSortByPrice(Arrays.asList("Lavender", "Herbs", "Apple"))
                    .forEach(i ->
                            log.info(i.getName()));
            log.info("");

            log.info(STRING_LINE);
            log.info("6. Count Shampoos by Price");
            log.info("counts all shampoos with price lower than a given price");
            log.info(STRING_LINE);
            log.warn("Enter price -> ");
            //log.info(String.format("%d", shampooService.findAllByPriceLowerThan(new BigDecimal(READER.readLine())).size()));
            log.info(String.format("%d", shampooService.findAllByPriceLowerThan(new BigDecimal(5.80)).size()));
            log.info("");

            log.info(STRING_LINE);
            log.info("7. Select Shampoos by Ingredients");
            log.info(" Selects all shampoos with ingredients in a given list");
            log.info(STRING_LINE);
            log.warn("Enter ingredients list on one line -> ");
            //shampooService.findAllByIngredientsIn(Arrays.asList(READER.readLine().split("\\s+"))).forEach(s ->
            shampooService.findAllByIngredientsIn(Arrays.asList("Berry","Mineral-Colagen")).forEach(s ->
                    log.info(s.getBrand()));
            log.info("");

            log.info(STRING_LINE);
            log.info("8. Select Shampoos by Ingredients Count");
            log.info(" selects all shampoos with ingredients less than a given number");
            log.info(STRING_LINE);
            log.warn("Enter maximum (exclusive) count of ingredients -> ");
            //shampooService.findAllWithIngredientsCountLessThan(Integer.parseInt(READER.readLine())).forEach(s ->
            shampooService.findAllWithIngredientsCountLessThan(2).forEach(s ->
                    log.info(s.getBrand()));
            log.info("");

            log.info(STRING_LINE);
            log.info("9. Select Ingredient Name and Shampoo Brand By Name");
            log.info(" selects all shampoo names and sum their ingredients prices.");
            log.info(STRING_LINE);
            log.warn("Enter shampoo brand -> ");
            //List<Double> prices = shampooService.findSumOfIngredients(Arrays.asList(READER.readLine().split(("\\s+"))));
            String brand = "Silk Comb";
            Double sum = shampooService.findSumOfIngredients(brand);
            log.info(String.format("%s %.2f", brand, sum));
            log.info("");


            log.info(STRING_LINE);
            log.info("10. Delete Ingredients by name");
            log.info(" deletes ingredients by a given name.");
            log.info(STRING_LINE);
            log.warn("Enter ingredient name -> ");
            String name = "Deleted";
            BasicIngredient ingr = ingredientsService.findByName(name);
            if(ingr != null) {
                //ingredientsService.deleteByName(name);
                log.info(String.format("Deleted %s ID %d", ingr.getName(), ingr.getId()));
            }
            log.info("");

            log.info(STRING_LINE);
            log.info("11. Update Ingredients by price");
            log.info(" increases the price of all ingredients by 10%.");
            log.info(STRING_LINE);
            log.warn("Enter ingredient name -> ");
            ingredientsService.increaseAllPricesWithPercent(10.0);

            log.info("");
            log.info(STRING_LINE);
            log.info("12. Update Ingredients by Names");
            log.info(" increases the price of given ingredients by 10%.");
            log.info(STRING_LINE);
            log.warn("Enter ingredient names in separated by ", " -> ");
            //List<String> names = Arrays.asList(READER.readLine().split(", "));
            List<String> names = Arrays.asList("Lavender, Herbs, Apple, Berry, Mineral-Colagen".split(", "));
            ingredientsService.increaseAllPricesWithPercent(10.0, names);

            log.info("");

            log.info("");
            log.info(STRING_LINE);
            log.info("Program Ended!");
        };
    }
}
