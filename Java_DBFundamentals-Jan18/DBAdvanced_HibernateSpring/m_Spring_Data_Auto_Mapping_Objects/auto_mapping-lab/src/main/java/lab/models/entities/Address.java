package lab.models.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "addresses")
public class Address {

    private Long id;
    private City city;
    private String street;
    private Set<Employee> occupants;

    public Address() {
        this.occupants = new HashSet<>();
    }

    public Address(City city, String street) {
        this();
        this.city = city;
        this.street = street;
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

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id")
    public City getCity() {
        return this.city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Column(nullable = false)
    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @OneToMany(mappedBy = "address")
    public Set<Employee> getOccupants() {
        return this.occupants;
    }

    public void setOccupants(Set<Employee> occupants) {
        this.occupants = occupants;
    }

    @Override
    public String toString() {
        return "AddressDAO{" +
                "id=" + this.id +
                ", city=" + this.city +
                ", street='" + this.street + '\'' +
                ", occupants=" + String.join(", ", this.occupants
                .stream().map(o ->
                        String.format("%s %s",
                                o.getFirstName(),
                                o.getLastName()))
                .collect(Collectors.toList())) +
                '}';
    }
}
