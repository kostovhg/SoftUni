package soft_uni.bookshop_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import soft_uni.bookshop_system.enums.AgeRestriction;
import soft_uni.bookshop_system.enums.EditionType;
import soft_uni.bookshop_system.models.entities.Author;
import soft_uni.bookshop_system.models.entities.Book;
import soft_uni.bookshop_system.models.entities.Category;
import soft_uni.bookshop_system.models.services.AuthorServiceImpl;
import soft_uni.bookshop_system.models.services.BookServiceImpl;
import soft_uni.bookshop_system.models.services.CategoryServiceImpl;
import sun.net.www.MimeTable;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private static final String RESOURCE_FILES_FOLDER = System.getProperty("user.dir") + "/src/main/resources/seedFiles/";
    private static final String AUTHORS_RESOURCE_FILE = "authors.txt";
    private static final String BOOKS_RESOURCE_FILE = "books.txt";
    private static final String CATEGORIES_RESOURCE_FILE = "categories.txt";

    private AuthorServiceImpl authorService;
    private CategoryServiceImpl categoryService;
    private BookServiceImpl bookService;

    @Autowired
    public ConsoleRunner(AuthorServiceImpl authorService, CategoryServiceImpl categoryService, BookServiceImpl bookService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        //System.out.println(" ------------------ \n test \n ------------------");

        if(this.authorService.getAllAuthors().size() == 0) {
            seedDatabase();
        }

        /* 1. Get all books after the year 2000. Print only their titles. */
        System.out.println("+------------------------------------------------+");
        System.out.println("- Print all book titles released after year 2000 -");
        System.out.println("+------------------------------------------------+");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        for (Book book : this.bookService.getAllAfterDate(formatter.parse("2000-01-01"))) {
            System.out.println(book.getTitle());
        }
        System.out.println();

        /* 2. Get all authors with at least one book with release date before 1990.
        Print their first name and last name. */
        System.out.println("+------------------------------------------------+");
        System.out.println("- Print all authors names with book before 1990  -");
        System.out.println("+------------------------------------------------+");
        for (Author author : this.authorService.getAllWithBooksBefore(formatter.parse("1990-01-01"))){
            System.out.println(String.format("%s %s", author.getFirstName(), author.getLastName()));
        }
        System.out.println();

        /* 3. Get all authors, ordered by the number of their books (descending).
        Print their first name, last name and book count. */
        System.out.println("+------------------------------------------------+");
        System.out.println("- Print all authors ordered by their books count -");
        System.out.println("+------------------------------------------------+");
        for (Author author : this.authorService.getAllOrderByBooksCount()) {
            System.out.println(
                    String.format("%s %s books - %d",
                            author.getFirstName(),
                            author.getLastName(),
                            author.getBooks().size()));
        }
        System.out.println();

        /* 4. Get all books from author George Powell,
        ordered by their release date (descending),
        then by book title (ascending). Print the book's title, release date and copies.*/
        System.out.println("+------------------------------------------------+");
        System.out.println("- Get all books from George Powell ordered by:   -");
        System.out.println("- their release date (desc), then by title(asc)  -");
        System.out.println("+------------------------------------------------+");
        Author author = this.authorService.getAuthorByFullName("George Powell");
        System.out.println("Books by George Powell:");
        for (Book book : this.bookService.getAllForAuthorNameAndOrder(author)) {
            System.out.println(
                    String.format("Title: %s, \n\tRelease Date: %s, Copies: %d",
                            book.getTitle(),
                            formatter.format(book.getReleaseDate()),
                            book.getCopies()));
        }
        System.out.println();
    }

    private List<Author> seedAuthors(String file) throws IOException {

        // It should be Set<Author> but that requires implementation for Author.equal(Author otherAuthor)
        // Because in the problem description it is not mentioned, it will stay as List
        List<Author> authors = new ArrayList<>();
        List<String> allAuthorsAsString = Files.readAllLines(Paths.get(RESOURCE_FILES_FOLDER  + file));

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

    private List<Category> seedCategories(String file) throws IOException {

        List<Category> categories = new ArrayList<>();
        List<String> allCategoriesAsString = Files.readAllLines(Paths.get(RESOURCE_FILES_FOLDER  + file));

        for (String line : allCategoriesAsString) {
            if(!line.trim().equals("")){
            categories.add(new Category(line));
            }
        }

        this.categoryService.saveCategoryIntoDb(categories);
        return categories;
    }

    private void seedDatabase() throws IOException, ParseException {

        List<Author> authors = seedAuthors(AUTHORS_RESOURCE_FILE);
        List<Category> categories = seedCategories(CATEGORIES_RESOURCE_FILE);
        List<Book> books = new ArrayList<>();

        Random random = new Random();

        AgeRestriction[] ageRestrictions = AgeRestriction.values();

        BufferedReader booksReader = new BufferedReader(new InputStreamReader(
                new FileInputStream(new File(
                        RESOURCE_FILES_FOLDER +
                                BOOKS_RESOURCE_FILE))));
        String line;// = booksReader.readLine();

        while ((line = booksReader.readLine()) != null) {
            String[] data = line.split("\\s+");

            int authorIndex = random.nextInt(authors.size());
            Author author = authors.get(authorIndex);

            // Strangely there is \uFEFF character in front of the first record "1"
            EditionType editionType =
                    EditionType.values()[Integer.parseInt(data[0].trim().replaceAll("[\uFEFF-\uFFFF]", ""))];

            SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
            Date releaseDate = formatter.parse(data[1]);
            int copies = Integer.parseInt(data[2]);
            BigDecimal price = new BigDecimal(data[3]);
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(data[4])];
            StringBuilder titleBuilder = new StringBuilder();

            for (int i = 5; i < data.length; i++) {
                titleBuilder.append(data[i]).append(" ");
            }
            titleBuilder.delete(titleBuilder.lastIndexOf(" "), titleBuilder.lastIndexOf(" "));
            String title = titleBuilder.toString();

            Book book = new Book();
            book.setAuthor(author);
            book.setEditionType(editionType);
            book.setReleaseDate(releaseDate);
            book.setCopies(copies);
            book.setPrice(price);
            book.setAgeRestriction(ageRestriction);
            book.setTitle(title);

            for (int i = 0; i < random.nextInt(categories.size() - 1); i++) {
                book.getCategories().add(categories.get(random.nextInt(categories.size())));
            }

            books.add(book);
        }
        // We should change all cascade types for relations to CascadeType.MERGE
        // because we are creating some of entities before others and it gives
        // "detached entity passed to persist" when final save try to new objects into the database
        this.bookService.saveBookIntoDb(books);
    }
}
