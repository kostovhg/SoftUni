package system_bookshop.services;

import system_bookshop.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system_bookshop.models.entities.Category;
import system_bookshop.services.cotracts.CategoryService;

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
