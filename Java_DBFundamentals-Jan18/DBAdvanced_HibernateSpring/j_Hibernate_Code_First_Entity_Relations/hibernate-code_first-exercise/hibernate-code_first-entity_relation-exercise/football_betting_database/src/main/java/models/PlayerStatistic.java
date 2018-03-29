package models;

import javax.persistence.*;
import java.util.Set;

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


}