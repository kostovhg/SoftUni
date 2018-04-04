package system_bookshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;
import system_bookshop.models.entities.Author;
import system_bookshop.models.entities.Book;
import system_bookshop.models.entities.dto.ReducedBook;
import system_bookshop.models.enums.AgeRestriction;
import system_bookshop.models.enums.EditionType;
import system_bookshop.repositories.BookRepository;
import system_bookshop.services.cotracts.BookService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /*
        Basic methods
     */

    //<editor-fold desc="Basic methods">
    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public void remove(Book book) {
        this.bookRepository.delete(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public void saveBookIntoDb(Book book) {
        this.bookRepository.save(book);
    }

    @Override
    public void saveBookIntoDb(List<Book> book) {
        this.bookRepository.saveAll(book);
    }
    //</editor-fold>

    /*
        Custom methods
     */

    //<editor-fold desc="Custom methods">


    @Override
    public Stream<Book> getAllAfterDate(Date date) {
        return this.bookRepository.findAllByReleaseDateAfterOrderByBookId(date);
    }

    @Override
    public String getAllAfterDate(String stringDate) throws ParseException {
        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(stringDate);
        // overloading existing method
        return this.getResult(this.getAllAfterDate(date)
                .map(Book::getTitle)
        );
    }

    @Override
    public Stream<Book> findAllForAuthorNameAndOrder(Author author) {
        return this.bookRepository.findAllByAuthorOrderByReleaseDateDescTitleAsc(author);
    }

    @Override
    public String findAllForAuthorNameAndOrder(String authorName) {
        String[] name = authorName.split("\\s+");
        return this.getResult(this.bookRepository
                .findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitleAsc(name[0], name[1])
                .map(Book::getTitle));
    }

    //     Advanced querying exercise

    @Override
    public Stream<Book> findAllByAgeRestriction(AgeRestriction ageRestriction) {
        return this.bookRepository
                .findAllByAgeRestrictionEquals(ageRestriction);
    }

    @Override
    public String findAllByAgeRestriction(String ageRestriction) {
        AgeRestriction ar = AgeRestriction.valueOf(ageRestriction.toUpperCase());
        return this.getResult(this.bookRepository.
                findAllByAgeRestrictionEquals(ar)
                .map(Book::getTitle));
    }

    @Override
    public Stream<Book> findAllByEditionTypeAndCopiesLessThan(EditionType type, Integer count) {
        return this.bookRepository.findAllByEditionTypeAndCopiesLessThan(type, count);
    }

    @Override
    public String findAllByEditionTypeAndCopiesLessThan(String type, String count) {
        // independent method
        EditionType et = EditionType.valueOf(type.toUpperCase());
        Integer c = Integer.valueOf(count);
        return this.getResult(this.bookRepository
                .findAllByEditionTypeAndCopiesLessThan(et, c).map(Book::getTitle));
    }

    @Override
    public Stream<Book> findAllByPriceLowerThanOrPriceGreaterThan(BigDecimal upperBound, BigDecimal lowerBound) {
        return this.bookRepository.findAllByPriceLessThanOrPriceGreaterThan(upperBound, lowerBound);
    }

    @Override
    public String findAllByPriceLowerThanOrPriceGreaterThan(String upperBound, String lowerBound) {
        // overloading existing method
        return this.getResult(this.findAllByPriceLowerThanOrPriceGreaterThan(
                new BigDecimal(upperBound),
                new BigDecimal(lowerBound))
                .map(Book::getTitle));
    }

    @Override
    public Stream<Book> findAllByReleaseDateYearIsNotIn(String year) {
        return this.bookRepository.findAllByReleaseDateYearIsNotIn(year);
    }

    @Override
    public Stream<Book> findAllByReleaseDateYearIsNotIn(Integer year) {
        return this.bookRepository.findAllByReleaseDateYearIsNotIn(year);
    }

    @Override
    public String findAllByReleaseDateNotInYear(String year) {
        return this.getResult(this.findAllByReleaseDateYearIsNotIn(year).map(Book::getTitle));
    }

    @Override
    public String findAllByReleaseDateNotInYear(Integer year) {
        return this.getResult(this.findAllByReleaseDateYearIsNotIn(year).map(Book::getTitle));
    }


    @Override
    public Stream<Book> findAllByReleaseDateBefore(Date date) {
        return this.bookRepository.findAllByReleaseDateBeforeOrderByBookId(date);
    }

    @Override
    public String findAllByReleaseDateBefore(String date) throws ParseException {
        Date d = new SimpleDateFormat("dd-MM-yyyy").parse(date);
        return this.getResult(this.findAllByReleaseDateBefore(d)
                .map(Book::getTitle));
    }

    @Override
    public Stream<Book> findAllByTitleContaining(String st) {
        return this.bookRepository
                .findAllByTitleContaining(st);
    }

    @Override
    public String listAllByTitleContaining(String st) {
        return this.getResult(this.bookRepository
                .findAllByTitleContaining(st)
                .map(Book::getTitle));
    }

    @Override
    public Stream<Book> findAllByAuthor_LastNameStartsWith(String lastNameStart) {
        return this.bookRepository
                .findAllByAuthor_LastNameStartsWithOrderByAuthorFirstName(lastNameStart);
    }

    @Override
    public String listAllByAuthor_LastNameStartsWith(String lastNameStart) {
        return this.getResult(this.findAllByAuthor_LastNameStartsWith(lastNameStart)
                .map(s -> String.format("%s (%s)", s.getTitle(), s.getAuthor().getFullName())));
    }

    @Override
    public Long findAllByTitleLengthGreaterThan(Integer length) {
        return this.bookRepository.numberOfBooksWithTitleLongerThan(length);
    }

    @Override
    public String findAllByTitleLengthGreaterThan(String length) {
        return String.valueOf(this.findAllByTitleLengthGreaterThan(Integer.valueOf(length)));
    }

    @Override
    public List<ReducedBook> reduceBook(String title) {
        List<ReducedBook> reducedBooks = this.bookRepository.reduceBook(title)
                .map(ob -> new ReducedBook() {
                    @Override
                    public String getTitle() {
                        return ob[0].toString();
                    }

                    @Override
                    public String getEditionType() {
                        return ob[1].toString();
                    }

                    @Override
                    public String getRestrictionType() {
                        return ob[2].toString();
                    }

                    @Override
                    public String getPrice() {
                        return ob[3].toString();
                    }
                }).collect(Collectors.toList());
        return reducedBooks;
    }

    @Override
    public String increaseCopiesForBooksReleasedAfterWith(Date date, Integer plusCopies) {
        Integer booksReleasedAfter = this.bookRepository
                .increaseBookCopiesReleasedAfter(date, plusCopies);

        return String.format("%s books are released after %s so total of %d book copies were added",
                booksReleasedAfter,
                new SimpleDateFormat("dd MMM yyyy").format(date),
                booksReleasedAfter * plusCopies);
    }

    @Override
    public String removeBooksWithCopiesCount(int count) {
        return String.valueOf(this.bookRepository.deleteAllByCopiesLessThan(count));
    }

    //</editor-fold>

    private String getResult(Stream<String> stream) {
        StringBuilder sb = new StringBuilder();
        stream.forEach(s -> sb.append(s).append(System.lineSeparator()));
        return sb.toString().trim();
    }
}
