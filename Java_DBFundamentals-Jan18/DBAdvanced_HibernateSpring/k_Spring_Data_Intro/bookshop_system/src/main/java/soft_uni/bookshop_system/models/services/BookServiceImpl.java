package soft_uni.bookshop_system.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soft_uni.bookshop_system.models.entities.Book;
import soft_uni.bookshop_system.models.repositories.BookRepository;

import javax.transaction.Transactional;
import java.util.List;

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
}
