package system_bookshop.services.cotracts;


import system_bookshop.models.entities.Author;
import system_bookshop.models.entities.Category;

import java.util.List;
import java.util.stream.Stream;

public interface CategoryService {

    void saveCategoryIntoDb(Category author);

    void saveCategoryIntoDb(List<Category> category);

}
