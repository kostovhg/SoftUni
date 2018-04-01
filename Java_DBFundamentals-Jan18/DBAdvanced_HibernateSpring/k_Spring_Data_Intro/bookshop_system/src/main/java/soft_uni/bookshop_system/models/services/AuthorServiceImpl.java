package soft_uni.bookshop_system.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soft_uni.bookshop_system.models.entities.Author;
import soft_uni.bookshop_system.models.entities.Category;
import soft_uni.bookshop_system.models.repositories.AuthorRepository;

import javax.transaction.Transactional;
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
    public void saveAuthorIntoDb(Author author){
        this.authorRepository.save(author);
    }

    @Override
    public void saveAuthorIntoDb(List<Author> author){
        this.authorRepository.saveAll(author);
    }
}
