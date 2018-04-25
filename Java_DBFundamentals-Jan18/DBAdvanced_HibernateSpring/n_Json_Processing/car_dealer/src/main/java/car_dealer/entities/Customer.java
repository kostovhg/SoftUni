package car_dealer.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer {

    private Long id;
    private String name;
    private Date birthDate;
    private boolean isYoungDriver;
    private Set<Sale> purchases;

    public Customer() {
        this.purchases = new HashSet<>();
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

    @Column(name = "name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "birth_date")
    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Column(name = "is_young_driver")
    public boolean isYoungDriver() {
        return this.isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        this.isYoungDriver = youngDriver;
    }

    @OneToMany(mappedBy = "customer")
    public Set<Sale> getPurchases() {
        return this.purchases;
    }

    public void setPurchases(Set<Sale> purchases) {
        this.purchases = purchases;
    }
}
