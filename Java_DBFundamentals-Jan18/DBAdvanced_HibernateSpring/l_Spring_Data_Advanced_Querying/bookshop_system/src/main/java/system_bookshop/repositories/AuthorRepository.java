package system_bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Repository;
import system_bookshop.models.entities.Author;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

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

    Stream<Author> findAllByFirstNameEndsWith(String nameEnd);

    @Query("SELECT" +
            "  concat(a.firstName, ' ', a.lastName, ' - ', sum(b.copies)) " +
            "from Author as a " +
            "  join a.books As b " +
            "group by a.authorId " +
            "order by sum(b.copies) desc")
    Stream<String> listAllAuthorsByTotalSumOfBookCopies();

}
