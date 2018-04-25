package xmlProcessingE.domain.model;


import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {

    private Long id;
    private String name;
    private BigDecimal price;
    private User buyer;
    private User seller;
    private Set<Category> categories;

    public Product() {
        this.categories = new HashSet<>();
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
    @Length(min = 3)
    @Column(name = "name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @Column(name = "price")
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @ManyToOne
    @JoinColumn(name = "buyer_id", referencedColumnName = "id")
    public User getBuyer() {
        return this.buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    @NotNull
    @ManyToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    public User getSeller() {
        return this.seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "products_categories",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    public Set<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
