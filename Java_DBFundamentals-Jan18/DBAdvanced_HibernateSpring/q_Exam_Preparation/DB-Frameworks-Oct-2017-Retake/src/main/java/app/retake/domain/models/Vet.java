package app.retake.domain.models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vets")
public class Vet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    @Length(min = 3, max = 40)
    private String name;

    @Column(name = "profession", nullable = false)
    @Length(min = 3, max = 50)
    private String profession;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "vet")
    private Set<Procedure> procedures;

    public Vet() {
        this.procedures = new HashSet<>();
    }

    // id – integer number, primary identification field
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // name – a string with minimum size of 3 characters and no longer than 40

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //profession – a string with minimum size of 3 characters and no longer than 50
    public String getProfession() {
        return this.profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    // age – integer number, minimum value of 22 years and maximum 65
    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    /* phoneNumber – required, make sure it matches the following requirements:
        either starts with +359 and 9 digits following
        consists of exacly 10 digits, starting with 0 */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<Procedure> getProcedures() {
        return this.procedures;
    }

    public void setProcedures(Set<Procedure> procedures) {
        this.procedures = procedures;
    }
}
