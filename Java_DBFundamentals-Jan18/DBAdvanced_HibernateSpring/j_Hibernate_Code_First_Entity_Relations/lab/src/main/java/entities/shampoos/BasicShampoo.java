package entities.shampoos;


import contracts.Shampoo;
import entities.labels.BasicLabel;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "shampoos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "shampoo_type", discriminatorType = DiscriminatorType.STRING)
public abstract class BasicShampoo implements Shampoo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Basic
    private String brand;

    @Basic
    private BigDecimal price;

//    @Enumerated
//    private Size size;

//    @OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "label_id", referencedColumnName = "id")
//    private BasicLabel label;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinTable(name = "shampoo_ingredients",
//            joinColumns = @JoinColumn(name = "shampoo_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id"))
//    private Set<BasicIngredient> ingredients;

    protected BasicShampoo() {
        //this.setIngredients(new HashSet<>());
    }

    BasicShampoo(String brand, BigDecimal price, BasicLabel label){
        this();
        this.brand = brand;
        this.price = price;
        this.size = size;
        //this.label = label;
    }

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

//    @Override
//    public Size getSize() {
//        return this.size;
//    }
//
//    @Override
//    public void setSize(Size size) {
//        this.size = size;
//    }

//    @Override
//    public BasicLabel getLabel() {
//        return this.label;
//    }
//
//    @Override
//    public void setLabel(BasicLabel label) {
//        this.label = label;
//    }
//
//    @Override
//    public Set<BasicIngredient> getIngredients() {
//        return this.ingredients;
//    }
//
//    @Override
//    public void setIngredients(Set<BasicIngredient> ingredients) {
//        this.ingredients = ingredients;
//    }
}
