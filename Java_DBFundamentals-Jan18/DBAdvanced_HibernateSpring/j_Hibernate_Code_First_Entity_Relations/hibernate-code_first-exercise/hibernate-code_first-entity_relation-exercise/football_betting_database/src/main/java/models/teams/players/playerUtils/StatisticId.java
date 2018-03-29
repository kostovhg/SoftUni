package models.teams.players.playerUtils;

import models.games.Game;
import models.teams.players.Player;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StatisticId implements Serializable {

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "player_id")
    private Player player;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "game_id")
    private Game game;

    public StatisticId(){

    }

    public StatisticId(Game game, Player player){
        this.game = game;
        this.player = player;
    }


    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        StatisticId tmpId = (StatisticId) o;
        if(this.player.getId() != tmpId.player.getId()) return false;
        return this.game.getId() == tmpId.game.getId();
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.player, this.game);
    }
}


