package soft_uni.user_system.models.entities.townEntity;

import javax.persistence.*;

@Entity
@Table(name = "towns")
public class Town {

    private Long townId;
    private String townName;
    private Country country;

    public Town() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "town_id", columnDefinition = "INT(11) UNSIGNED")
    public Long getId() {
        return this.townId;
    }

    public void setId(Long id) {
        this.townId = id;
    }

    @Column(name = "town_name")
    public String getName() {
        return this.townName;
    }

    public void setName(String name) {
        this.townName = name;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="country_id")
    public Country getCountry() {
        return this.country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
