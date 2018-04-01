package soft_uni.bookshop_system.models.services;


import soft_uni.bookshop_system.models.entities.Author;

import java.util.List;

public interface AuthorService {

    void saveAuthorIntoDb(Author author);

    void saveAuthorIntoDb(List<Author> author);
}
