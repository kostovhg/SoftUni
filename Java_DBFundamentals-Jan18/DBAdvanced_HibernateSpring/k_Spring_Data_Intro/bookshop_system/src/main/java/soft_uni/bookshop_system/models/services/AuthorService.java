package soft_uni.bookshop_system.models.services;


import soft_uni.bookshop_system.models.entities.Author;

import java.util.Date;
import java.util.List;

public interface AuthorService {

    void saveAuthorIntoDb(Author author);

    void saveAuthorIntoDb(List<Author> author);

    List<Author> getAllAuthors();

    List<Author> getAllWithBooksBefore(Date date);

    List<Author> getAllOrderByBooksCount();

    Author getAuthorByFullName(String george_powell);
}
