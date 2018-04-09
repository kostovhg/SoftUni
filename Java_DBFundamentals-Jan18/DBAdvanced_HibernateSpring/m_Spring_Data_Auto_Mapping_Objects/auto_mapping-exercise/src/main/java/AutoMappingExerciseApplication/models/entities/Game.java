package AutoMappingExerciseApplication.models.entities;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game {

    private Long id;
    private String title;
    private String trailer; // YouTube Video Id
    private String thumbnailURL; // URL
    private BigDecimal size;
    private BigDecimal price;
    private String description;
    private Date releaseDate;
    private Set<User> users;
    private Set<Order> orders;

    public Game() {
        this.users = new HashSet<>();
        this.orders = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT(11) UNSIGNED")
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @Size(min = 3, max = 100, message = "Title mus be between 3 and 100 symbols")
    @Pattern(regexp = "^[A-Z]+.*$", message = "Title must begin with uppercase letter")
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Size(min = 11)
    @Column(name = "video_id", length = 11)
    public String getTrailer() {
        return this.trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    @Basic
    @Pattern(regexp = "^https?://.*")
    public String getImageThumbnail() {
        return this.thumbnailURL;
    }

    public void setImageThumbnail(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    @Column(name = "size", scale = 2)
    @DecimalMin(value = "0.1", message = "Size must be a positive number")
    public BigDecimal getSize() {
        return this.size;
    }

    public void setSize(BigDecimal size) {
        this.size = size;
    }

    @NotNull
    @Column(name = "price", scale = 2)
    @DecimalMin(value = "0.01", message = "Price must be a positive number")
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Size(min = 20)
    @Column(name = "Descritpion", columnDefinition = "TEXT")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    public Date getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @ManyToMany(mappedBy = "games")
    public Set<User> getUsers() {
        return this.users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @ManyToMany(mappedBy = "products")
    public Set<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;

        Game game = (Game) o;
        return this.id == game.id &&
                (this.title != null ?
                        this.title.equals(game.title) :
                        game.title == null);
    }

    @Override
    public int hashCode(){
        return this.title.hashCode();
    }
}
