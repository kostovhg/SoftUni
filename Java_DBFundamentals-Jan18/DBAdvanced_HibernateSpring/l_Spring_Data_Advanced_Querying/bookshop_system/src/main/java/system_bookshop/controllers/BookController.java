package system_bookshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import system_bookshop.io.Reader;
import system_bookshop.models.entities.Book;
import system_bookshop.models.entities.dto.ReducedBook;
import system_bookshop.models.enums.AgeRestriction;
import system_bookshop.models.enums.EditionType;
import system_bookshop.services.cotracts.BookService;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BookController {

    private final Reader reader;

    private final BookService bookService;

    private DateFormat dateFormat = new SimpleDateFormat("d/M/yyyy");

    @Autowired
    public BookController(Reader reader, BookService bookService) {
        this.reader = reader;
        this.bookService = bookService;
    }

    public List<Book> getBooks(String file) throws IOException {

        return this.reader.read(file).stream()
                .map(s -> {
                    String[] data = s.split("\\s+", 6);

                    EditionType editionType =
                            EditionType.values()[Integer.parseInt(data[0].trim())];

                    Date releaseDate = null;
                    try {
                        releaseDate = dateFormat.parse(data[1]);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    int copies = Integer.parseInt(data[2]);
                    BigDecimal price = new BigDecimal(data[3]);
                    AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(data[4])];
                    String title = data[5];

                    Book book = new Book();
                    book.setEditionType(editionType);
                    book.setReleaseDate(releaseDate);
                    book.setCopies(copies);
                    book.setPrice(price);
                    book.setAgeRestriction(ageRestriction);
                    book.setTitle(title);

                    return book;
                }).collect(Collectors.toList());
    }

    public void seedBooks(List<Book> books) {
        this.bookService.saveBookIntoDb(books);
    }


    public String getAllByAgeRestriction(String ageRestriction) {
        return this.bookService.findAllByAgeRestriction(ageRestriction);
    }

    public String getAllByEditionTypeAndCopiesLessThan(String type, String copiesCount) {
        return this.bookService.findAllByEditionTypeAndCopiesLessThan(type, copiesCount);
    }


    public String findAllByPriceNotBetween(String upper, String lower) {
        return this.bookService.findAllByPriceLowerThanOrPriceGreaterThan(upper, lower);
    }

    public String findAllByReleaseDateNotInYear(String year) {
        return this.bookService.findAllByReleaseDateNotInYear(year);
    }

    public String findAllByReleaseDateBefore(String date) throws ParseException {
        return this.bookService.findAllByReleaseDateBefore(date);
    }

    public String listAllByTitleContaining(String searchString) {
        return this.bookService.listAllByTitleContaining(searchString);
    }

    public String listAllByAuthor_LastNameStartWith(String lastNameStart) {
        return this.bookService.listAllByAuthor_LastNameStartsWith(lastNameStart);
    }

    public String countAllByTitleLengthGreaterThan(String length) {
        return this.bookService.findAllByTitleLengthGreaterThan(length);
    }

    public String reduceBook(String title) {
        List<ReducedBook> reducedBooks = this.bookService.reduceBook(title);
        StringBuilder sb = new StringBuilder();
        for (ReducedBook reducedBook : reducedBooks) {
            sb.append(String.format("%s %s %s %s",
                    reducedBook.getTitle().trim(),
                    reducedBook.getEditionType().trim(),
                    reducedBook.getRestrictionType().trim(),
                    reducedBook.getPrice()))
                    .append(System.lineSeparator());
        }
        return sb.toString().trim();
    }

    public String increaseCopiesOfBooksAfter(String date, String copies) {
        try {
            Date after = new SimpleDateFormat("dd MMM yyyy").parse(date);
            Integer number = Integer.valueOf(copies);
            return this.bookService.increaseCopiesForBooksReleasedAfterWith(after, number);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Invalid input");
        }
    }

    public String numberOfBooksByAuthorFullName(String fullName){
        Integer countOfBooks = this.bookService.numberOfBooksByAuthor(fullName);
        switch (countOfBooks){
            case 0: return String.format("%s has not written any books yet", fullName);
            case 1: return String.format("%s has written 1 book", fullName);
            default: return String.format("%s has written %d books", fullName, countOfBooks);
        }
    }

    public String removeBooksWithCopiesCount(String input){
        return this.bookService.removeBooksWithCopiesCount(Integer.parseInt(input));
    }
}
