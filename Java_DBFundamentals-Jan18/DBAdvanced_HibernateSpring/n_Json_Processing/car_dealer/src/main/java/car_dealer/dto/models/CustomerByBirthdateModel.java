package car_dealer.dto.models;

import car_dealer.entities.Sale;
import com.google.gson.annotations.Expose;

import javax.persistence.OneToMany;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CustomerByBirthdateModel implements Serializable {

    @Expose
    private Long id;

    @Expose
    private String name;

    @Expose
    private Date birthDate;

    @Expose
    private boolean youngerDriver;

    @Expose
    @OneToMany(mappedBy = "customer")
    private Set<Sale> purchases;

    public CustomerByBirthdateModel(Long id) {
        this.purchases = new HashSet<>();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungerDriver() {
        return this.youngerDriver;
    }

    public void setYoungerDriver(boolean youngerDriver) {
        this.youngerDriver = youngerDriver;
    }

    public Set<Sale> getPurchases() {
        return this.purchases;
    }

    public void setPurchases(Set<Sale> purchases) {
        this.purchases = purchases;
    }
}