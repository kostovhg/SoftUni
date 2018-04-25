package xmlProcessingE.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmlProcessingE.domain.dto.DTOConvertUtil;
import xmlProcessingE.domain.dto.binding.categories.CategoryCreateBindingModel;
import xmlProcessingE.domain.model.Category;
import xmlProcessingE.domain.model.User;
import xmlProcessingE.repository.CategoryRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void create(Category category) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void saveAll(List<CategoryCreateBindingModel> categoriesModel) {
        List<Category> categories = DTOConvertUtil.convert(categoriesModel, Category.class);
        this.categoryRepository.saveAll(categories);
    }

    @Override
    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public void save(Category category) {
        this.categoryRepository.save(category);
    }
}
