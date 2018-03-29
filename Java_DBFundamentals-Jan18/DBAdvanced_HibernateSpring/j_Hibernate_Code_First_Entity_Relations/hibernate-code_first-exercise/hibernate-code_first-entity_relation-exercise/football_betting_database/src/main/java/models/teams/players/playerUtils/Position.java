package models.teams.players.playerUtils;

import models.teams.players.Player;

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

    public Position() {
    }

    public Position(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Player> getPlayers() {
        return this.players;
    }
}
