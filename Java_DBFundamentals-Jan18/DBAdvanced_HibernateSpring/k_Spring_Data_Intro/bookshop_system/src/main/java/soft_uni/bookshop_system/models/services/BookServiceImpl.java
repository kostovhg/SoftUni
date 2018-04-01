package soft_uni.bookshop_system.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soft_uni.bookshop_system.models.entities.Author;
import soft_uni.bookshop_system.models.entities.Book;
import soft_uni.bookshop_system.models.repositories.BookRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void saveBookIntoDb(Book book){
        this.bookRepository.save(book);
    }

    @Override
    public void saveBookIntoDb(List<Book> book){
        this.bookRepository.saveAll(book);
    }

    @Override
    public List<Book> getAllBooks(){
        return this.bookRepository.findAll();
    }

    @Override
    public Set<Book> getAllAfterDate(Date date) {
        return this.bookRepository.getAllByReleaseDateAfterOrderByBookId(date);
    }

    @Override
    public List<Book> getAllForAuthorNameAndOrder(Author author) {
        return this.bookRepository.findAllByAuthorOrderByReleaseDateDescTitleAsc(author);
    }
}
