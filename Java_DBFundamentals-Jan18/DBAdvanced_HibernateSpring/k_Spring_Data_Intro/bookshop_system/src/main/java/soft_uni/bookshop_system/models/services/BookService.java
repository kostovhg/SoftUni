package soft_uni.bookshop_system.models.services;

import soft_uni.bookshop_system.models.entities.Book;

import java.util.List;

public interface BookService {

    void saveBookIntoDb(Book author);

    void saveBookIntoDb(List<Book> book);
}
