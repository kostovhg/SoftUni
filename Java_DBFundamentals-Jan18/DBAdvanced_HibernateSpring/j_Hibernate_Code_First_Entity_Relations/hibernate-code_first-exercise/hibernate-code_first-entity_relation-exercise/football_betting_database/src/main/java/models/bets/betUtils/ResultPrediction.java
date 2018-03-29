package models.bets.betUtils;

import models.bets.betUtils.Prediction;

import javax.persistence.*;

@Entity
@Table(name = "result_predictions")
public class ResultPrediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "prediction")
    private Prediction prediction;

    public ResultPrediction() {
    }

    public ResultPrediction(Prediction prediction) {
        this.prediction = prediction;
    }
}
