package models.games;

import models.teams.Team;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_team_id")
    private Team homeTeam;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "away_team_id")
    private Team awayTeam;

    @Column(name = "home_goals")
    private int homeGoals;

    @Column(name = "away_goals")
    private int awayGoals;

    @Column(name = "date_and_time")
    private Date dateAndTime;

    @Column(name = "home_team_win_bet_rate")
    private BigDecimal homeTeamWinBetRate;

    @Column(name = "away_team_win_bet_rate")
    private BigDecimal awayTeamWinBetRate;

    @Column(name = "draw_team_win_bet_rate")
    private BigDecimal drawTeamWinBetRate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "round_id")
    private Round roud;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competition_id")
    private Competition competition;

    public int getId() {
        return this.id;
    }

    public Game() {
    }
}
