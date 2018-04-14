package products_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import products_shop.domain.model.Product;

public interface CategoryRepository extends JpaRepository<Product, Long>, CategoryRepositoryCustom {

}
