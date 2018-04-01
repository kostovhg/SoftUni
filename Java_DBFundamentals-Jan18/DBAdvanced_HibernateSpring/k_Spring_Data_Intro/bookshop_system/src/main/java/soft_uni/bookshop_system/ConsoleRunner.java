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

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class ConsoleRunner implements CommandLineRunner {

    public static final String RESOURCE_FILES_FOLDER = System.getProperty("user.dir") + "/src/main/resources/seedFiles/";
    public static final String AUTHORS_RESOURCE_FILE = "authors.txt";
    public static final String BOOKS_RESOURCE_FILE = "books.txt";
    public static final String CATEGORIES_RESOURCE_FILE = "categories.txt";

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

        seedDatabase();

    }

    private List<Author> seedAuthors(String file) throws IOException {
        /*
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(new File(
                                RESOURCE_FILES_FOLDER +
                                AUTHORS_RESOURCE_FILE)
                ))));
        */
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
