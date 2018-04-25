package xmlProcessingE.service;

import xmlProcessingE.domain.dto.binding.categories.CategoryCreateBindingModel;
import xmlProcessingE.domain.model.Category;
import xmlProcessingE.domain.model.User;

import java.util.List;

public interface CategoryService {

    void create(Category category);

    void saveAll(List<CategoryCreateBindingModel> categories);

    List<Category> findAll();

    void save(Category category);
}
