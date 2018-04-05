package system_bookshop.services.cotracts;


import system_bookshop.models.entities.Author;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public interface AuthorService {

    void saveAuthorIntoDb(Author author);

    void saveAuthorIntoDb(List<Author> author);

    List<Author> getAllAuthors();

    List<Author> getAllWithBooksBefore(Date date);

    List<Author> getAllOrderByBooksCount();

    Author getAuthorByFullName(String george_powell);

    Stream<Author> findAllByFirstNameEndsWith(String nameEnd);

    String listAllByFirstNameEndsWith(String nameEnd);

    String listAllAuthorsByTotalBookCopies();
}
