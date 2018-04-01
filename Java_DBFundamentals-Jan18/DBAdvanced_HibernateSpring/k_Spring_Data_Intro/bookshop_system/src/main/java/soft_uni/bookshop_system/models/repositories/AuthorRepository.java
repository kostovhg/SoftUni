package soft_uni.bookshop_system.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import soft_uni.bookshop_system.models.entities.Author;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("Select \n" +
            "   a\n" +
            "FROM Author AS a \n" +
            "JOIN a.books AS b \n" +
            "ON b.releaseDate < :date \n" +
            "ORDER BY a.authorId")
    List<Author> findAllByBooksBefore(@Param("date") Date date);

    // Fetch type should be EAGER for field books (OneToMany) in entity Author
    // or we will receive "failed to lazily initialize a collection of ..."
    @Query(
            "SELECT \n" +
                    "    a \n" +
                    "FROM Author AS a \n" +
                    //"JOIN a.books AS b ON b.author = a.authorId \n " +
                    //"GROUP BY a.authorId \n" +
                    "ORDER BY size(a.books) DESC")
    List<Author> getAllByBooksCount();

    Author getAuthorByFirstNameAndLastName(String firstName, String lastName);
}
