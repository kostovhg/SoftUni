package models.games.gamesUtils;
import models.games.gamesUtils.Competition;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "competition_type")
public class CompetitionTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "type")
    private Set<Competition> competitions;

    public CompetitionTypes() {
    }

    public CompetitionTypes(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Competition> getCompetitions() {
        return this.competitions;
    }
}
