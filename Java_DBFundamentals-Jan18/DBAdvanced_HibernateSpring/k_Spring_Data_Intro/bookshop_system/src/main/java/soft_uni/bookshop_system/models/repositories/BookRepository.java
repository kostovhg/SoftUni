package soft_uni.bookshop_system.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soft_uni.bookshop_system.models.entities.Author;
import soft_uni.bookshop_system.models.entities.Book;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Set<Book> getAllByReleaseDateAfterOrderByBookId(Date date);

    List<Book> findAllByAuthorOrderByReleaseDateDescTitleAsc(Author author);
}
