package system_bookshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import system_bookshop.io.Reader;
import system_bookshop.models.entities.Author;
import system_bookshop.services.cotracts.AuthorService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AuthorController {

    private final Reader reader;

    private final AuthorService authorService;

    @Autowired
    public AuthorController(Reader reader, AuthorService authorService) {
        this.reader = reader;
        this.authorService = authorService;
    }

    public List<Author> seedAuthors(String file) throws IOException {

        // It should be Set<Author> but that requires implementation for Author.equal(Author otherAuthor)
        // Because in the problem description it is not mentioned, it will stay as List
        List<Author> authors = new ArrayList<>();
        List<String> allAuthorsAsString = Files.readAllLines(Paths.get(file));

        for (String line : allAuthorsAsString) {
            String[] names = line.trim().split("\\s+");
            authors.add(
                    (names.length > 1 ?
                            new Author(names[0], names[1]) :
                            new Author(names[0])
                    ));
        }

        this.authorService.saveAuthorIntoDb(authors);
        return authors;
    }

    public List<Author> getAllAuthors() {
        return this.authorService.getAllAuthors();
    }

    public String listAllByAuthorFirstNameEndsWith(String nameEnd){
        return this.authorService.listAllByFirstNameEndsWith(nameEnd);
    }

    public String listAllByTotalBooksCopies(){
        return this.authorService.listAllAuthorsByTotalBookCopies();
    }

    public String countOfAllBooksByAuthor(String fullName){
        return this.authorService.totalNumberOfBooksByAuthor(fullName);
    }
}
