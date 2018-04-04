package system_bookshop.repositories;

import com.sun.jndi.toolkit.ctx.Continuation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import system_bookshop.models.entities.Author;
import system_bookshop.models.entities.Book;
import system_bookshop.models.enums.AgeRestriction;
import system_bookshop.models.enums.EditionType;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.stream.Stream;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Stream<Book> findAllByAuthorOrderByReleaseDateDescTitleAsc(Author author);

    Stream<Book> findAllByReleaseDateAfterOrderByBookId(Date date);

    Stream<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitleAsc(String firstName, String lastName);

    Stream<Book> findAllByAgeRestrictionEquals(AgeRestriction restriction);

    Stream<Book> findAllByEditionTypeAndCopiesLessThan(EditionType type, Integer copies);

    Stream<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lower, BigDecimal upper);

    @Query("SELECT b FROM Book AS b WHERE substring(b.releaseDate, 1, 4) NOT LIKE :date")
    Stream<Book> findAllByReleaseDateYearIsNotIn(@Param("date") String date);

    @Query("SELECT b FROM Book AS b WHERE year(b.releaseDate) != :year")
    Stream<Book> findAllByReleaseDateYearIsNotIn(@Param("year") int year);

    Stream<Book> findAllByReleaseDateBeforeOrderByBookId(Date date);

    Stream<Book> findAllByAuthor_LastNameEndsWith(String ending);

    Stream<Book> findAllByTitleContaining(String word);

    Stream<Book> findAllByAuthor_LastNameStartsWithOrderByAuthorFirstName(String nameStart);

    @Query("SELECT count(b.title) FROM Book AS b WHERE LENGTH(b.title) > :length")
    Long numberOfBooksWithTitleLongerThan(@Param("length") int length);

    @Query("SELECT b.title, b.editionType, b.ageRestriction, b.price " +
            " FROM Book AS b WHERE b.title = :title")
    Stream<Object[]> reduceBook(@Param("title") String title);

    @Modifying
    @Transactional
    @Query("UPDATE Book AS b SET b.copies = b.copies + :number WHERE b.releaseDate > :date")
    Integer increaseBookCopiesReleasedAfter(@Param("date") Date date,@Param("number") Integer number);

    @Transactional
    int deleteAllByCopiesLessThan(int count);
}
