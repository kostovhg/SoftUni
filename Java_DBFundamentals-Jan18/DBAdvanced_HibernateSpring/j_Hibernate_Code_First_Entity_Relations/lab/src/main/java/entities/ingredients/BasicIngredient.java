package entities.ingredients;

import contracts.Ingredient;
import entities.shampoos.BasicShampoo;
import enums.Size;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "ingredients")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ingredient_type", discriminatorType = DiscriminatorType.STRING)
public abstract class BasicIngredient implements Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToMany(mappedBy = "ingredients", cascade = CascadeType.ALL)
    private List<BasicShampoo> shampoos;

    protected BasicIngredient() {
    }

    protected BasicIngredient(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
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
    public List<BasicShampoo> getShampoos() {
        return shampoos;
    }

    @Override
    public void setShampoos(List<BasicShampoo> shampoos) {
        this.shampoos = shampoos;
    }
}
