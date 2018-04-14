package products_shop.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import products_shop.domain.model.Category;
import products_shop.repository.CategoryRepositoryCustom;

import javax.persistence.EntityManager;

public class CategoryRepositoryImpl implements CategoryRepositoryCustom {

    @Autowired
    private EntityManager em;

    @Override
    public Category merge(Category category) {
        return em.merge(category);
    }
}
