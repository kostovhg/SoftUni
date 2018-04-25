package xmlProcessingE.service;

import xmlProcessingE.domain.dto.binding.product.ProductCreateBindingModel;
import xmlProcessingE.domain.model.Product;
import xmlProcessingE.repository.CategoryRepository;
import xmlProcessingE.repository.UserRepository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by User on 30.7.2017 г..
 */
public interface ProductService {

    void create(Product product);

    List<Product> getAllByRangeWithoutBuyer(BigDecimal lBound, BigDecimal uBound);

    void saveAll(List<ProductCreateBindingModel> productModels, UserService userService, CategoryService categoryService);
}
