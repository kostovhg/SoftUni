package models.teams.players;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "possitions")
public class Position {

    @Id
    @Column(name = "id", columnDefinition = "VARCHAR(2) UNIQUE")
    private String id;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "position")
    private Set<Player> players;

}
