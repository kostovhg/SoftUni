package system_bookshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system_bookshop.models.entities.Author;
import system_bookshop.repositories.AuthorRepository;
import system_bookshop.services.cotracts.AuthorService;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @PersistenceContext
    private EntityManager entityManager;

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
    public List<Author> getAllWithBooksBefore(Date date) {
        return this.authorRepository.findAllByBooksBefore(date);
    }

    @Override
    public List<Author> getAllOrderByBooksCount() {
        return this.authorRepository.getAllByBooksCount();
    }

    @Override
    public Author getAuthorByFullName(String george_powell) {
        String[] fullName = george_powell.split("\\s+");
        return this.authorRepository.getAuthorByFirstNameAndLastName(fullName[0], fullName[1]);
    }

    @Override
    public Stream<Author> findAllByFirstNameEndsWith(String nameEnd) {
        return this.authorRepository.findAllByFirstNameEndsWith(nameEnd);
    }

    @Override
    public String listAllByFirstNameEndsWith(String nameEnd) {
        return this.getResult(this.findAllByFirstNameEndsWith(nameEnd).map(Author::getFullName));
    }

    @Override
    public String listAllAuthorsByTotalBookCopies() {
        return this.getResult(this.authorRepository
                .listAllAuthorsByTotalSumOfBookCopies());
    }

    private String getResult(Stream<String> stream) {
        StringBuilder sb = new StringBuilder();
        stream.forEach(s -> sb.append(s).append(System.lineSeparator()));
        return sb.toString().trim();
    }
}
