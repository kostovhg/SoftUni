package products_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import products_shop.domain.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {

}
