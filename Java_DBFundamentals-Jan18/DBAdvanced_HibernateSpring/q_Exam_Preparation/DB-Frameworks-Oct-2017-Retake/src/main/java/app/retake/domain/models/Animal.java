package app.retake.domain.models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "animals")
public class Animal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    @Length(min = 3, max = 20)
    private String name;

    @Column(name = "type", nullable = false)
    @Length(min = 3, max = 20)
    private String type;

    @Column(name = "age")
    private Integer age;

    @OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="passport_serial_number", foreignKey=@ForeignKey(name = "fk_animal_passport_serial_number"))
    private Passport passport;

    @OneToMany(mappedBy = "animal")
    private Set<Procedure> procedures;

    public Animal() {
        this.procedures = new HashSet<>();
    }

    // – integer number, primary identification field
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // – a string with minimum size of 3 characters and no longer than 20, require
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // – type of the animal, e.g “cat”, “dog”, “parrot” еtc; a string consisting of no more than 20 characters and longer or equal to 3, required
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // – integer value, cannot be negative or 0
    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    // – the passport of the animal
    public Passport getPassport() {
        return this.passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public Set<Procedure> getProcedures() {
        return this.procedures;
    }

    public void setProcedures(Set<Procedure> procedures) {
        this.procedures = procedures;
    }

    @PrePersist
    private void setRelation(){
        this.passport.setAnimal(this);
    }
}
