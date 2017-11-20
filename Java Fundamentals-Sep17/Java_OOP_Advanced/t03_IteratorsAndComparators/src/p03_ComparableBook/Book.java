package p03_ComparableBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Book implements Comparable<Book>{

    private String title;
    private int year;
    private List<String> authors;

    public Book(String title, int year, String... authors) {
        this.setTitle(title);
        this.setYear(year);
        this.setAuthors(authors);
    }

    private void setTitle(String title){
        this.title = title;
    }

    private void setYear(int year) {
        this.year = (year);
    }

    private void setAuthors(String... authors){
        if(authors != null){
            this.authors = new ArrayList<>(Arrays.asList(authors));
            return;
        }
        this.authors = new ArrayList<>();
    }

    public String getTitle(){
        return this.title;
    }

    public int getYear(){
        return this.year;
    }

    public List<String> getAuthors() {
        return Collections.unmodifiableList(this.authors);
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", year=" + year +
                ", authors=" + authors +
                '}';
    }

    @Override
    public int compareTo(Book book) {
        if(this.title.compareTo(book.title) == 0){
            if(this.year == book.year) return 0;
            else if(this.year < book.year) return 1;
            else return -1;
        } else {
            return book.title.compareTo(this.title);
        }
    }
}
