package products_shop.repository;

import products_shop.domain.model.Category;

public interface CategoryRepositoryCustom {

    Category merge(Category category);
}
