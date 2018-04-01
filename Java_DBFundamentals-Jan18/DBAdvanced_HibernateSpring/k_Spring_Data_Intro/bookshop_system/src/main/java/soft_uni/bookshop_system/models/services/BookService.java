package soft_uni.bookshop_system.models.services;

import soft_uni.bookshop_system.models.entities.Author;
import soft_uni.bookshop_system.models.entities.Book;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface BookService {

    void saveBookIntoDb(Book author);

    void saveBookIntoDb(List<Book> book);

    List<Book> getAllBooks();

    Set<Book> getAllAfterDate(Date date);

    List<Book> getAllForAuthorNameAndOrder(Author author);
}
