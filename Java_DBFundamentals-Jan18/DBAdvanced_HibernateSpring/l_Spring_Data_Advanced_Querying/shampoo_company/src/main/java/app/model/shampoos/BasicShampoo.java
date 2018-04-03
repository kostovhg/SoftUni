package app.model.shampoos;

import app.model.enums.Size;
import app.model.ingredients.BasicIngredient;
import app.model.labels.BasicLabel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shampoos")
public class BasicShampoo implements Shampoo {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @Basic
    private BigDecimal price;

    @Basic
    private String brand;

    @Enumerated
    private Size size;

    protected BasicShampoo(){this.setIngredients(new HashSet<>());}
    BasicShampoo(String brand, BigDecimal price, Size size, BasicLabel classicLabel) {
        this();
        this.brand = brand;
        this.price = price;
        this.size = size;
        this.label = classicLabel;
    }

    @OneToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "label", referencedColumnName = "id")
    private BasicLabel label;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "shampoos_ingredients",
    joinColumns = @JoinColumn(name = "shampoo_id",
    referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "ingredient_id",
    referencedColumnName = "id"))
    private Set<BasicIngredient> ingredients;

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getBrand() {
        return this.brand;
    }

    @Override
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public BigDecimal getPrice() {
        return this.price;
    }

    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public Size getSize() {
        return this.size;
    }

    @Override
    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public BasicLabel getLabel() {
        return this.label;
    }

    @Override
    public void setLabel(BasicLabel label) {
        this.label = label;
    }

    @Override
    public Set<BasicIngredient> getIngredients() {
        return this.ingredients;
    }

    @Override
    public void setIngredients(Set<BasicIngredient> ingredients) {
        this.ingredients = ingredients;
    }
}
