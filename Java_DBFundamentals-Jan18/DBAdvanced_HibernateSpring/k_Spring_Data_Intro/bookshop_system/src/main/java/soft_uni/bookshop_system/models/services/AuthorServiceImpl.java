package soft_uni.bookshop_system.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soft_uni.bookshop_system.models.entities.Author;
import soft_uni.bookshop_system.models.entities.Book;
import soft_uni.bookshop_system.models.repositories.AuthorRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void saveAuthorIntoDb(Author author) {
        this.authorRepository.save(author);
    }

    @Override
    public void saveAuthorIntoDb(List<Author> author) {
        this.authorRepository.saveAll(author);
    }

    @Override
    public List<Author> getAllAuthors() {
        return this.authorRepository.findAll();
    }

    @Override
    public List<Author> getAllWithBooksBefore(Date date){
        return this.authorRepository.findAllByBooksBefore(date);
    }

    @Override
    public List<Author> getAllOrderByBooksCount(){
        return this.authorRepository.getAllByBooksCount();
    }

    @Override
    public Author getAuthorByFullName(String george_powell) {
        String[] fullName = george_powell.split("\\s+");
        return this.authorRepository.getAuthorByFirstNameAndLastName(fullName[0], fullName[1]);
    }
}
