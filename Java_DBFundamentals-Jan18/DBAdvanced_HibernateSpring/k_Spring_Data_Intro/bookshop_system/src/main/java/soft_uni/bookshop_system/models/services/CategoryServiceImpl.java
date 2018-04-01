package soft_uni.bookshop_system.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soft_uni.bookshop_system.models.entities.Category;
import soft_uni.bookshop_system.models.repositories.CategoryRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void saveCategoryIntoDb(Category category){
        this.categoryRepository.save(category);
    }

    @Override
    public void saveCategoryIntoDb(List<Category> category){
        this.categoryRepository.saveAll(category);
    }
}
