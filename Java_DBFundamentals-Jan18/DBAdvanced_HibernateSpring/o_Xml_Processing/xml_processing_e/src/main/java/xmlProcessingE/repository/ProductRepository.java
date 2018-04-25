package xmlProcessingE.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import xmlProcessingE.domain.model.Category;
import xmlProcessingE.domain.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByPriceBetweenAndBuyerIdIsNullOrderByPrice(BigDecimal bigDecimal, BigDecimal bigDecimal1);
}
