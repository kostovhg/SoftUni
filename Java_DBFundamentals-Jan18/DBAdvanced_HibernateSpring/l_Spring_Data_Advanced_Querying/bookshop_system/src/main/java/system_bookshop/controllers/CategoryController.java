package system_bookshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import system_bookshop.io.Reader;
import system_bookshop.models.entities.Category;
import system_bookshop.services.cotracts.CategoryService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CategoryController {

    private final Reader reader;

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(Reader reader, CategoryService categoryService) {
        this.reader = reader;
        this.categoryService = categoryService;
    }

    public List<Category> seedCategories(String file) throws IOException {

        List<Category> categories = new ArrayList<>();
        List<String> allCategoriesAsString = Files.readAllLines(Paths.get(file));

        for (String line : allCategoriesAsString) {
            if (!line.trim().equals("")) {
                categories.add(new Category(line));
            }
        }
        this.categoryService.saveCategoryIntoDb(categories);
        return categories;
    }

}
