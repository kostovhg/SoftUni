package app.retake.domain.models;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "animal_aids")
public class AnimalAid implements Serializable {

    /* id – integer number, primary identification field
    name – a string containing at least 3 characters with
    price – decimal value, cannot be a negative number or 0 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "price", nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @ManyToMany
    @JoinTable(name = "animal_aids_procedures",
            joinColumns =
            @JoinColumn(name = "animal_aid_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "procedure_id", referencedColumnName = "id"))
    private Set<Procedure> procedures;

    public AnimalAid() {
        this.procedures = new HashSet<>();
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<Procedure> getProcedures() {
        return this.procedures;
    }

    public void setProcedures(Set<Procedure> procedures) {
        this.procedures = procedures;
    }
}
