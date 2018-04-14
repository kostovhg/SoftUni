package products_shop.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import products_shop.domain.model.Product;
import products_shop.repository.ProductRepository;
import products_shop.service.ProductService;

import javax.transaction.Transactional;

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
        this.productRepository.merge(product);
    }
}
