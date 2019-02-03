package chushka.domain.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import static chushka.utils.Constants.*;

@Entity(name = "products")
public class Product extends BaseEntity {

    private String name;
    private String description;
    private ProductType type;

    public Product() {
    }

    @Column(name = NAME_PROPERTY)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = DESCRIPTION_PROPERTY)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = TYPE_PROPERTY)
    public ProductType getType() {
        return this.type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public void setType(String type){
        this.type = ProductType.fromString(type);
        //this.type = ProductType.valueOf(type.toUpperCase());
    }
}
