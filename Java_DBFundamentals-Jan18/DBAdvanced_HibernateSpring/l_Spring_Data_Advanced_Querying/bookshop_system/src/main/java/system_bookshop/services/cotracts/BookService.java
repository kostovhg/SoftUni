package system_bookshop.services.cotracts;

import system_bookshop.models.entities.dto.ReducedBook;
import system_bookshop.models.enums.AgeRestriction;
import system_bookshop.models.enums.EditionType;
import system_bookshop.models.entities.Author;
import system_bookshop.models.entities.Book;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public interface BookService {

    Optional<Book> findById(Long id);

    List<Book> getAllBooks();

    void remove(Book book);

    void saveBookIntoDb(Book author);

    void saveBookIntoDb(List<Book> book);

    Stream<Book> getAllAfterDate(Date date);

    String getAllAfterDate(String stringDate) throws ParseException;

    Stream<Book> findAllForAuthorNameAndOrder(Author author);

    String findAllForAuthorNameAndOrder(String authorName);

    Stream<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    String findAllByAgeRestriction(String ageRestriction);

    Stream<Book> findAllByEditionTypeAndCopiesLessThan(EditionType type, Integer count);

    String findAllByEditionTypeAndCopiesLessThan(String type, String count);

    Stream<Book> findAllByPriceLowerThanOrPriceGreaterThan(BigDecimal upperBound, BigDecimal lowerBound);

    String findAllByPriceLowerThanOrPriceGreaterThan(String upperBound, String lowerBound);

    Stream<Book> findAllByReleaseDateYearIsNotIn(String year);

    Stream<Book> findAllByReleaseDateYearIsNotIn(Integer year);

    String findAllByReleaseDateNotInYear(String year);

    String findAllByReleaseDateNotInYear(Integer year);

    Stream<Book> findAllByReleaseDateBefore(Date date);

    String findAllByReleaseDateBefore(String date) throws ParseException;

    Stream<Book> findAllByTitleContaining(String st);

    String listAllByTitleContaining(String st);

    Stream<Book> findAllByAuthor_LastNameStartsWith(String lastNameStart);

    String listAllByAuthor_LastNameStartsWith(String lastNameStart);

    Long findAllByTitleLengthGreaterThan(Integer length);

    String findAllByTitleLengthGreaterThan(String length);

    List<ReducedBook> reduceBook(String title);

    String increaseCopiesForBooksReleasedAfterWith(Date date, Integer plusCopies);

    String removeBooksWithCopiesCount(int i);

    Integer numberOfBooksByAuthor(String name);
}
