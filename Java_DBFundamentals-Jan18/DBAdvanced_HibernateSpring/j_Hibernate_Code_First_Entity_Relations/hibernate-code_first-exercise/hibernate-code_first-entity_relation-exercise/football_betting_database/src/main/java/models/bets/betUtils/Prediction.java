package models.bets.betUtils;

import javax.persistence.Enumerated;

public enum Prediction {
    HOME(1, "Home Team Win"),
    DRAW(0, "Draw Game"),
    AWAY(2, "Away Team Win");

    private int value;
    private String desc;

    Prediction(int value, String desc){
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }
}
