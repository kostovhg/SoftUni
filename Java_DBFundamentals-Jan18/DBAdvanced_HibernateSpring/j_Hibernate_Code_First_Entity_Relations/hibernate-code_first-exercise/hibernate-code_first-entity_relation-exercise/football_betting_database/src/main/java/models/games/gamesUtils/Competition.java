package models.games.gamesUtils;

import models.games.Game;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "competitions")
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competition_type_id")
    private CompetitionTypes type;

    @OneToMany(mappedBy = "competition")
    private Set<Game> games;

    public Competition() {
    }

    public Competition(String name, CompetitionTypes type) {
        this.name = name;
        this.type = type;
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

    public CompetitionTypes getType() {
        return this.type;
    }

    public void setType(CompetitionTypes type) {
        this.type = type;
    }
}
