package xmlProcessingE.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import xmlProcessingE.domain.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {


}
