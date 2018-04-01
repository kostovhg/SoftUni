package soft_uni.bookshop_system.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soft_uni.bookshop_system.models.entities.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
