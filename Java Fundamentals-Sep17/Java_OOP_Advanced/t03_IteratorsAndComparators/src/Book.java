import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Book implements Comparable<Book> {

    private String title;
    private int year;
    private List<String> authors;

    public Book(String title, int year, String... authors) {
        this.setTitle(title);
        this.setYear(year);
        this.setAuthors(authors);
    }

    private void setTitle(String title) {
        this.title=title;
    }

    private void setYear(int year) {
        this.year=(year);
    }

    private void setAuthors(String... authors) {
        if (authors != null) {
            this.authors=new ArrayList<>(Arrays.asList(authors));
            return;
        }
        this.authors=new ArrayList<>();
    }

    public String getTitle() {
        return this.title;
    }

    public int getYear() {
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
    public int compareTo(Book anotherBook) {
        int firstComparison=this.getTitle().compareTo(anotherBook.getTitle());
        if (firstComparison == 0) {
            if (this.getYear() < anotherBook.getYear()) {
                return -1;
            } else if (this.getYear() > anotherBook.getYear()) {
                return 1;
            }
            return 0;
        } else {
            return firstComparison;
        }
    }
}
