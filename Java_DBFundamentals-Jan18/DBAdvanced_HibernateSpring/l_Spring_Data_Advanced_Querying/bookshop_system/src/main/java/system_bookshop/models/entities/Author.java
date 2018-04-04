package system_bookshop.models.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authors")
@NamedStoredProcedureQueries(
        {@NamedStoredProcedureQuery(name = "get_authors_books",
        procedureName = "total_number_of_books_by_author",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "inParameter", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "outParameter", type = Integer.class)
        })}
)
public class Author {

    private Long authorId;
    private String firstName;
    private String lastName;
    private Set<Book> books;

    public Author() {
        this.books = new HashSet<>();
    }

    public Author(String lastName) {
        this();
        this.lastName = lastName;
    }

    public Author(String firstName, String lastName) {
        this(lastName);
        this.firstName = firstName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    @Basic
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(nullable = false, name = "last_name")
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @OneToMany(mappedBy = "author", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    public Set<Book> getBooks() {
        return this.books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Transient
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }
}
