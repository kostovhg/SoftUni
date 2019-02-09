package metube.domain.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import static metube.utils.Constants.TUBE_EF_ID;

/**
 * A base entity containing common parameters for all entities
 * like id
 */

@MappedSuperclass
public abstract class BaseEntity {

    private String id;

    public BaseEntity() {
    }

    /*
    Generating UUID as a string with hibernate UUIDGenerator
     */
    @Id
    @GeneratedValue(generator = "uuid-string")
    @GenericGenerator(name = "uuid-string", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = TUBE_EF_ID, nullable = false, unique = true, updatable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
