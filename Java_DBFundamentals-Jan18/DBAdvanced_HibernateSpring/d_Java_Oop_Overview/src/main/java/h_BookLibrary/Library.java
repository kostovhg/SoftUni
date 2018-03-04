package h_BookLibrary;

import java.util.*;
import java.util.stream.Collectors;

public class Library{

    private String name;
    private List<Book> books;

    public Library(){
        this.name = "The Library of North-West literature society - Vratsa";
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public List<Book> getBooks(){
        return Collections.unmodifiableList(this.books);
    }

    public List<String> getAuthors() {
        return this.books
                .stream()
                .map(Book::getAuthor)
                .collect(Collectors.toList());
    }

    public Map<String, Double> getAllPricesGroupedByAuthor(){
        Map<String, Double> groupedPrices = new HashMap<>();
        for (Book book : this.getBooks()) {
            String author = book.getAuthor();
            Double price = book.getPrice().doubleValue();
           /* if(!groupedPrices.containsKey(author)){
                groupedPrices.put(author, price);
            } else {
                groupedPrices.get(book.getAuthor()) + price
            }*/
            groupedPrices.merge(author, price, Double::sum);
        }

        return groupedPrices;
    }
}