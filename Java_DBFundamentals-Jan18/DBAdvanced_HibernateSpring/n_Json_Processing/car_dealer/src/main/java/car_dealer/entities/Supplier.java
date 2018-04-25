package car_dealer.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "suppliers")
public class Supplier {

    private Long id;
    private String name;
    private boolean isImporter;
    private Set<Part> parts;

    public Supplier() {
        this.parts = new HashSet<>();
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

    @Column(name = "is_importer")
    public boolean isImporter() {
        return this.isImporter;
    }

    public void setImporter(boolean importer) {
        this.isImporter = importer;
    }

    @OneToMany(mappedBy = "supplier")
    public Set<Part> getParts() {
        return this.parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }
}
