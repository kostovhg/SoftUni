package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "store_location")
public class StoreLocation {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "location_name")
    private String locationName;

    @OneToMany(mappedBy = "storeLocation")
    private Set<Sale> sales;

    public StoreLocation(String locationName) {
        this.locationName = locationName;
    }

    public StoreLocation() {
    }
}
