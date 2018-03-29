package models.teams.players;

import models.teams.Team;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "squad_number")
    private String squadNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id")
    private Position position;

    @Column(name = "is_currently_injured")
    private boolean isCurrentlyInjured;

    @OneToMany(mappedBy = "player")
    private Set<PlayerStatistic> playerStatistics;

    public Player() {
    }

    public int getId() {
        return this.id;
    }
}