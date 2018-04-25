package xmlProcessingE.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmlProcessingE.domain.dto.DTOConvertUtil;
import xmlProcessingE.domain.dto.binding.product.ProductCreateBindingModel;
import xmlProcessingE.domain.model.Category;
import xmlProcessingE.domain.model.Product;
import xmlProcessingE.domain.model.User;
import xmlProcessingE.repository.CategoryRepository;
import xmlProcessingE.repository.ProductRepository;
import xmlProcessingE.repository.UserRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

/**
 * Created by User on 30.7.2017 г..
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void create(Product product) {
        this.productRepository.save(product);
    }

    @Override
    public List<Product> getAllByRangeWithoutBuyer(BigDecimal lBound, BigDecimal uBound) {
        return this.productRepository.findByPriceBetweenAndBuyerIdIsNullOrderByPrice(lBound, uBound);
    }

    @Override
    public void saveAll(List<ProductCreateBindingModel> productModels, UserService userService, CategoryService categoryService){
        Random random = new Random();
        List<Product> products = DTOConvertUtil.convert(productModels, Product.class);
        List<User> allUsers = userService.findAll();
        List<Category> allCategories = categoryService.findAll();
        for (Product product : products) {
            try {
                product.setSeller(allUsers.get(random.nextInt(allUsers.size())));
                if (random.nextInt(4) > 0) {
                    User buyer = allUsers.get(random.nextInt(allUsers.size()));
                    if (!product.getSeller().equals(buyer)) {
                        product.setBuyer(buyer);
                    }
                }
                for (int i = 0; i < random.nextInt(4) + 1; i++) {
                    int catIndex = random.nextInt(allCategories.size());
                    product.getCategories().add(allCategories.get(catIndex));
                }
//                for (Category category : product.getCategories()) {
//                    category.getProducts().add(product);
//                    categoryService.save(category);
//                }
                //this.productRepository.save(product);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        this.productRepository.saveAll(products);
    }
}
