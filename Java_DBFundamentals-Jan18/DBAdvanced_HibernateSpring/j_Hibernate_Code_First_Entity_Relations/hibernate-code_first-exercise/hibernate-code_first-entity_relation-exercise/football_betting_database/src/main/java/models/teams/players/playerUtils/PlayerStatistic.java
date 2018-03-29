package models.teams.players.playerUtils;

import models.games.Game;
import models.teams.players.Player;
import models.teams.players.playerUtils.StatisticId;

import javax.persistence.*;

@Entity
//@Table(name = "player_statistic")
@IdClass(StatisticId.class)
public class PlayerStatistic {

    @Id
    @ManyToOne
    @JoinColumn(name = "player")
    private Player player;

    @Id
    @ManyToOne
    @JoinColumn(name = "game")
    private Game game;

    @Column(name = "scored_goals")
    private int scoredGoals;

    @Column(name = "player_assists")
    private int playerAssists;

    @Column(name = "played_minutes_during_game")
    private int playedMinutesDuringGame;


    public PlayerStatistic() {
    }

    public PlayerStatistic(
            Player player,
            Game game,
            int scoredGoals,
            int playerAssists,
            int playedMinutesDuringGame) {
        this.player = player;
        this.game = game;
        this.scoredGoals = scoredGoals;
        this.playerAssists = playerAssists;
        this.playedMinutesDuringGame = playedMinutesDuringGame;
    }


}