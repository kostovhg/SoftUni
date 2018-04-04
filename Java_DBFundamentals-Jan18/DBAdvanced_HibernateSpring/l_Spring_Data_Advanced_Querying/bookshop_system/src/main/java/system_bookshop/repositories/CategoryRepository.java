package system_bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import system_bookshop.models.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {


}
