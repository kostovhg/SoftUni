package h_BookLibrary;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Book{

    private String title;
    private String author;
    private String publisher;
    private Date releaseDate;
    private String ISBNnumber;
    private BigDecimal price;

    public Book(String[] args){
        this.title = args[0];
        this.author = args[1];
        this.publisher = args[2];
        this.setReleaseDate(args[3]);
        this.ISBNnumber = args[4];
        this.price = new BigDecimal(args[5]);
    }

    private void setReleaseDate(String date){
        try {
            this.releaseDate = (new SimpleDateFormat("MM.dd.yyyy", Locale.ENGLISH)).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    /*
     name and a list of books. The books must contain the title, author, publisher, release date, ISBN-number and price.
Read a list of books, add them to the library and print the total sum of prices by author, ordered descending by price and then by author’s name lexicographically.
Books in the input will be in format {title} {author} {publisher} {release date} {ISBN} {price}.
     */


}