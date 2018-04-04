package system_bookshop.terminal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import system_bookshop.controllers.AuthorController;
import system_bookshop.controllers.BookController;
import system_bookshop.controllers.CategoryController;
import system_bookshop.models.entities.Author;
import system_bookshop.models.entities.Book;
import system_bookshop.models.entities.Category;
import system_bookshop.models.enums.AgeRestriction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static system_bookshop.models.enums.BookSystemEnums.getEnumAsStringList;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-mm-dd");
    private static final String RESOURCE_FILES_FOLDER = System.getProperty("user.dir") + "/src/main/resources/seedFiles/";
    private static final String AUTHORS_RESOURCE_FILE = "authors.txt";
    private static final String BOOKS_RESOURCE_FILE = "books.txt";
    private static final String CATEGORIES_RESOURCE_FILE = "categories.txt";
    private static int commandsExecuted = 1;

    private AuthorController authorController;
    private CategoryController categoryController;
    private BookController bookController;


    @Autowired
    public ConsoleRunner(
            AuthorController authorController,
            CategoryController categoryController,
            BookController bookController) {
        this.authorController = authorController;
        this.categoryController = categoryController;
        this.bookController = bookController;
    }

    @Override
    public void run(String... args) throws Exception {

        // Seed database if it is empty (authors is one of two entities that should be
        // seeded before books, so if it is empty, seedDatabase method have not been used)
        if (this.authorController.getAllAuthors().size() == 0) {
            seedDatabase();
        }

        String input = "";

        System.out.println();
        /* 1. A program that print titles of all books where
        their age restriction matches the given input (minor, teen or adult).
        Ignore casing of the input. */
        this.printHeading("Books Titles by Age Restriction");
        System.out.printf(
                "Enter age restriction (%s) (default \"miNor\"): ", getEnumAsStringList(AgeRestriction.class));
        input = READER.readLine();
        System.out.println(this.bookController.getAllByAgeRestriction(input.trim().isEmpty() ? "miNor" : input));
        input = "";

        System.out.println();
        /* 2. A program that print titles of the golden edition books and have less than 5000 copies */
        this.printHeading("Golden Books");
        System.out.println(this.bookController.
                getAllByEditionTypeAndCopiesLessThan("GOLD", "5000"));

        System.out.println();
        /* 3.  A program that print titles and price of books with price lower than 5 and higher than 40 */
        this.printHeading("Books by Price");
        System.out.println(this.bookController.
                findAllByPriceNotBetween("5", "40"));

        System.out.println();
        /* 4. A program that print titles of all books that are NOT released on given year */
        this.printHeading("Not Released Books");
        System.out.println(
                "Enter a year (default 1998): ");
        input = READER.readLine();
        System.out.println(this.bookController.
                findAllByReleaseDateNotInYear(input.isEmpty() ? "1998" : input));

        System.out.println();
        /* 5. print title, edition type and price of books that are released before given date
        * as an input from the console. The should be be in format dd-MM-yyyy */
        this.printHeading("Books Released Before Date");
        System.out.print(
                "Enter date in format dd-MM-yyyy (default 12-04-1992): ");
        input = READER.readLine();
        System.out.println(this.bookController
                .findAllByReleaseDateBefore(input.isEmpty() ? "12-04-1992" : input));

        System.out.println();
        /* 6. Print names of those authors whose first name edn with given string */
        this.printHeading("Authors Search");
        System.out.print(
                "Enter string for comparing with end of Authors first name (default \"e\"): ");
        input = READER.readLine();
        System.out.println(this.authorController
                .listAllByAuthorFirstNameEndsWith(input.isEmpty() ? "e" : input));

        System.out.println();
        /* 7. Print titles of books witch contains given string (regardless of the casing) */
        this.printHeading("Books Search");
        System.out.print(
                "Enter string to search among book titles (default \"sK\"): ");
        input = READER.readLine();
        System.out.println(this.bookController
                .listAllByTitleContaining(input.isEmpty() ? "sK" : input));

        System.out.println();
        /* 8. Print titles of books witch are written by authors whose last name start with given string */
        this.printHeading("Books Titles Search");
        System.out.print(
                "Enter string for comparing with start of Authors last name (default \"R\"):: ");
        input = READER.readLine();
        System.out.println(this.bookController
                .listAllByAuthor_LastNameStartWith(input.isEmpty() ? "R" : input));

        System.out.println();
        /* 9. Print number of books whose title is longer than a number as an input */
        this.printHeading("Books Titles Search");
        System.out.print(
                "Enter length minimum length of title (default \"12\"): ");
        input = READER.readLine();
        System.out.println(this.bookController
                .countAllByTitleLengthGreaterThan(input.isEmpty() ? "12" : input));

        System.out.println();
        /* 10. Print the total number of book copies by author, ordered descending by total books */
        this.printHeading("Total Book Copies");
        System.out.println(this.authorController
                .listAllByTotalBooksCopies());

        System.out.println();
        /* 11. Print information (title, edition type, age restriction and price) for book by given title.
         */
        this.printHeading("Reduce Book");
        System.out.print(
                "Enter book title (default \"Thrones\"): ");
        input = READER.readLine();
        System.out.println(this.bookController.reduceBook(input.isEmpty() ? "Thrones" : input));

        System.out.println();
        /* 12. Increase the copies of all books released after given date with given number
         */
        this.printHeading("* Increase Book Copies");
        System.out.print(
                "Enter date and copies to be added (default \"12 Oct 2005 100\"): ");
        input = READER.readLine();
        input = input.isEmpty() ? "12 Oct 2005 100" : input;
        int index = input.lastIndexOf(" ");
        String dateString = input.substring(0, index);
        String countString = input.substring(index + 1);
        System.out.println(this.bookController.increaseCopiesOfBooksAfter(dateString, countString));

        System.out.println();
        /* 13. Removes from the database those books whose copies are lower than given number
         */
        this.printHeading("* Remove Books");
        System.out.print(
                "Enter the amount of copies under witch all books will be deleted (default \"300\"): ");
        input = READER.readLine();
        input = input.isEmpty() ? "300" : input;
        String removedBooksCount = this.bookController.removeBooksWithCopiesCount(input);
        System.out.println(String.format("%s book%s deleted",
                removedBooksCount,
                removedBooksCount.matches("[01]") ? " was" : "s were"));

        System.out.println();
        /* 14. RPrints the total number of books that author has written by stored procedure
         */
        this.printHeading("* Remove Books");
        System.out.print(
                "Enter the full name of the author (default \"Amanda Rice\"): ");
        input = READER.readLine();
        input = input.isEmpty() ? "Amanda Rice" : input;
        String strCount = this.authorController.countOfAllBooksByAuthor(input);
        if(strCount.equals("0")){
            System.out.println(input + " has not written any books yet");
        } else {
            System.out.println(input + " has written " + strCount + " books");
        }
        System.out.println();


    }

    void printHeading(String s) {
        System.out.println("+------------------------------------------------+");
        System.out.println(String.format("+ %d - %s", commandsExecuted++, s));
        System.out.println("+------------------------------------------------+");
    }

    private void seedDatabase() throws IOException {

        List<Author> authors = this.authorController.seedAuthors(
                RESOURCE_FILES_FOLDER + AUTHORS_RESOURCE_FILE);
        List<Category> categories = this.categoryController.seedCategories(
                RESOURCE_FILES_FOLDER + CATEGORIES_RESOURCE_FILE);
        List<Book> books = this.bookController.getBooks(
                RESOURCE_FILES_FOLDER + BOOKS_RESOURCE_FILE);
        Random random = new Random();

        for (Book book : books) {
            book.setAuthor(authors.get(random.nextInt(authors.size())));
            int categoryCount = random.nextInt(categories.size());
            Set<Category> bookCategories = new HashSet<>();
            for (int i = 0; i < categoryCount; i++) {
                bookCategories.add(categories.get(random.nextInt(categories.size())));
            }
            book.setCategories(bookCategories);
        }
        // We should change all cascade types for relations to CascadeType.MERGE
        // because we are creating some of entities before others and it gives
        // "detached entity passed to persist" when final save try to new objects into the database
        this.bookController.seedBooks(books);
    }
}
