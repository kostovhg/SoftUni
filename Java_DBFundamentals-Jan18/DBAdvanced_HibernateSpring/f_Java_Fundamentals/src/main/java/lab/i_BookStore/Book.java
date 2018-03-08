package lab.i_BookStore;

public class Book {

    private String name;
    private String author;
    private Double price;

    public Book(String name, Double price, String author) {
        this.name = name;
        this.author = author;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public String getAuthor() {
        return this.author;
    }

    public Double getPrice() {
        return this.price;
    }
}
