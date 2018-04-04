package system_bookshop.models.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {

    private Long categoryId;
    private String name;
    private Set<Book> books;

    public Category() {
        this.books = new HashSet<>();
    }

    public Category(String name) {
        this();
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    public Long getId() {
        return this.categoryId;
    }

    public void setId(Long id) {
        this.categoryId = id;
    }

    @Column(nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "categories", targetEntity = Book.class)
    public Set<Book> getBooks() {
        return this.books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
