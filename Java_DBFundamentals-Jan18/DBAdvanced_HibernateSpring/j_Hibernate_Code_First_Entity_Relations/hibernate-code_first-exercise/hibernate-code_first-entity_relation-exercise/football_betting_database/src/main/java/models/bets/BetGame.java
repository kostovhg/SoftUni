package models.bets;

import models.games.Game;

import javax.persistence.*;

@Entity
@IdClass(BetGameId.class)
public class BetGame {

    @Id
    @ManyToOne
    @JoinColumn(name = "game")
    private Game game;

    @Id
    @ManyToOne
    @JoinColumn(name = "bet")
    private Bet bet;

    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "prediction_id")
    private ResultPrediction resultPrediction;
}
