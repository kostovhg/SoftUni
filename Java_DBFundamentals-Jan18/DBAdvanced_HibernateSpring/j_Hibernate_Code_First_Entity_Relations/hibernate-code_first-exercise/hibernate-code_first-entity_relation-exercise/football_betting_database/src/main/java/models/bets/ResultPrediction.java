package models.bets;

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


}
