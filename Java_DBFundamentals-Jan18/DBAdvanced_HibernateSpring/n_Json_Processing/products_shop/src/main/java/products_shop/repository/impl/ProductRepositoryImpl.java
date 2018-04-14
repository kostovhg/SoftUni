package products_shop.repository.impl;

import products_shop.domain.model.Category;
import products_shop.domain.model.Product;
import products_shop.repository.ProductRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * Created by User on 1.8.2017 г..
 */
@Repository
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    @Autowired
    private EntityManager em;

    @Override
    public Product merge(Product product) {
        return em.merge(product);
    }
}
