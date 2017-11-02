package p02_BookShop;

public class Book {
    private String title;

    private String author;
    private double price;

    public Book(String author, String title, double price) {
        this.setTitle(title);
        this.setAuthor(author);
        this.setPrice(price);
    }

    public String getTitle() {
        return title;
    }

    protected void setTitle(String title) throws IllegalArgumentException {
        // If the title’s length is less than 3 symbols –
        // exception’s message is: "Title not valid!"
        if ( title.length() < 3) {
            throw new IllegalArgumentException("Title not valid!");
        }
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    protected void setAuthor(String author) throws IllegalArgumentException {
        // If the author has two names and the second name is starting
        // with a digit– exception’s message is: "Author not valid!"
        if(author.matches("\\w+ [0-9]\\w+")) {
            throw new IllegalArgumentException("Author not valid!");
        }
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    protected void setPrice(double price) {
        // If the price is zero or it is negative –
        // exception’s message is: "Price not valid!"
        if (price < 0 ) {
            throw new IllegalArgumentException("Price not valid!");
        }
        this.price = price;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Type: ").append(this.getClass().getSimpleName())
                .append(System.lineSeparator())
                .append("Title: ").append(this.getTitle())
                .append(System.lineSeparator())
                .append("Author: ").append(this.getAuthor())
                .append(System.lineSeparator())
                .append("Price: ").append(this.getPrice())
                .append(System.lineSeparator());
        return sb.toString();
    }
}
