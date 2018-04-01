package soft_uni.bookshop_system.models.services;


import soft_uni.bookshop_system.models.entities.Category;

import java.util.List;

public interface CategoryService {

    void saveCategoryIntoDb(Category author);

    void saveCategoryIntoDb(List<Category> category);
}
