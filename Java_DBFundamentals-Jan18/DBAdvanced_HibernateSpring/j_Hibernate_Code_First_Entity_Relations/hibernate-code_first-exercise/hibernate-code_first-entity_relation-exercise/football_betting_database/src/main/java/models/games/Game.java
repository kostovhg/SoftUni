package models.games;

import models.games.gamesUtils.Competition;
import models.games.gamesUtils.Round;
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

    public Game() {
    }

    public Game(
            Team homeTeam,
            Team awayTeam,
            int homeGoals,
            int awayGoals,
            Date dateAndTime,
            BigDecimal homeTeamWinBetRate,
            BigDecimal awayTeamWinBetRate,
            BigDecimal drawTeamWinBetRate,
            Round roud,
            Competition competition) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
        this.dateAndTime = dateAndTime;
        this.homeTeamWinBetRate = homeTeamWinBetRate;
        this.awayTeamWinBetRate = awayTeamWinBetRate;
        this.drawTeamWinBetRate = drawTeamWinBetRate;
        this.roud = roud;
        this.competition = competition;
    }

    public int getId() {
        return this.id;
    }

    public Team getHomeTeam() {
        return this.homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return this.awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomeGoals() {
        return this.homeGoals;
    }

    public void setHomeGoals(int homeGoals) {
        this.homeGoals = homeGoals;
    }

    public int getAwayGoals() {
        return this.awayGoals;
    }

    public void setAwayGoals(int awayGoals) {
        this.awayGoals = awayGoals;
    }

    public Date getDateAndTime() {
        return this.dateAndTime;
    }

    public void setDateAndTime(Date dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public BigDecimal getHomeTeamWinBetRate() {
        return this.homeTeamWinBetRate;
    }

    public void setHomeTeamWinBetRate(BigDecimal homeTeamWinBetRate) {
        this.homeTeamWinBetRate = homeTeamWinBetRate;
    }

    public BigDecimal getAwayTeamWinBetRate() {
        return this.awayTeamWinBetRate;
    }

    public void setAwayTeamWinBetRate(BigDecimal awayTeamWinBetRate) {
        this.awayTeamWinBetRate = awayTeamWinBetRate;
    }

    public BigDecimal getDrawTeamWinBetRate() {
        return this.drawTeamWinBetRate;
    }

    public void setDrawTeamWinBetRate(BigDecimal drawTeamWinBetRate) {
        this.drawTeamWinBetRate = drawTeamWinBetRate;
    }

    public Round getRoud() {
        return this.roud;
    }

    public void setRoud(Round roud) {
        this.roud = roud;
    }

    public Competition getCompetition() {
        return this.competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }
}
