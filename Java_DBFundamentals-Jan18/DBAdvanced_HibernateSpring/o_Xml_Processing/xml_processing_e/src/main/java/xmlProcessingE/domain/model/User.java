package xmlProcessingE.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private Set<User> friends;
    private Set<Product> boughtProducts;
    private Set<Product> soldProducts;

    public User() {
        this.friends = new HashSet<>();
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

    @Column(name= "first_name")
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    @Size(min = 3)
    @Column(name = "last_name")
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "age")
    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @ManyToMany
    @JoinTable(name = "users_friends",
            joinColumns =
            @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "friend_id", referencedColumnName = "id"))
    public Set<User> getFriends() {
        return this.friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    @OneToMany(mappedBy = "buyer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<Product> getBoughtProducts() {
        return this.boughtProducts;
    }

    public void setBoughtProducts(Set<Product> boughtProducts) {
        this.boughtProducts = boughtProducts;
    }

    @OneToMany(mappedBy = "seller", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<Product> getSoldProducts() {
        return this.soldProducts;
    }

    public void setSoldProducts(Set<Product> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
