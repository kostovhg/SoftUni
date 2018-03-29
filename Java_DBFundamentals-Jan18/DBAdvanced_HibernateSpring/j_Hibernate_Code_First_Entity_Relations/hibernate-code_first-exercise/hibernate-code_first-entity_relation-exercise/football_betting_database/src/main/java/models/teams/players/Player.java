package models.teams.players;

import models.teams.Team;
import models.teams.players.playerUtils.PlayerStatistic;
import models.teams.players.playerUtils.Position;

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

    public Player(
            String name,
            String squadNumber,
            Team team,
            Position position,
            boolean isCurrentlyInjured) {
        this.name = name;
        this.squadNumber = squadNumber;
        this.team = team;
        this.position = position;
        this.isCurrentlyInjured = isCurrentlyInjured;
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

    public String getSquadNumber() {
        return this.squadNumber;
    }

    public void setSquadNumber(String squadNumber) {
        this.squadNumber = squadNumber;
    }

    public Team getTeam() {
        return this.team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isCurrentlyInjured() {
        return this.isCurrentlyInjured;
    }

    public void setCurrentlyInjured(boolean currentlyInjured) {
        this.isCurrentlyInjured = currentlyInjured;
    }

    public Set<PlayerStatistic> getPlayerStatistics(){
        return this.playerStatistics;
    }
}