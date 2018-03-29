package models.bets;

import models.bets.betUtils.BetGameId;
import models.bets.betUtils.ResultPrediction;
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

    public BetGame() {
    }

    public BetGame(Game game, Bet bet, ResultPrediction resultPrediction) {
        this.game = game;
        this.bet = bet;
        this.resultPrediction = resultPrediction;
    }

    public Game getGame() {
        return this.game;
    }

    public Bet getBet() {
        return this.bet;
    }

    public ResultPrediction getResultPrediction() {
        return this.resultPrediction;
    }

    public void setResultPrediction(ResultPrediction resultPrediction) {
        this.resultPrediction = resultPrediction;
    }
}
