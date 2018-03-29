package models.bets;

import models.bets.betUtils.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "bets")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "bet_money", nullable = false)
    private BigDecimal betMoney;

    @Column(name = "date_and_time_of_bet")
    private Date dateAndTimeOfBet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Bet() {
    }

    public Bet(BigDecimal betMoney, Date dateAndTimeOfBet) {
        this.betMoney = betMoney;
        this.dateAndTimeOfBet = dateAndTimeOfBet;
    }

    public int getId() {
        return this.id;
    }

    public BigDecimal getBetMoney() {
        return this.betMoney;
    }

    public void setBetMoney(BigDecimal money){
        this.betMoney  = money;
    }

    public Date getDateAndTimeOfBet() {
        return this.dateAndTimeOfBet;
    }

    public void setDateAndTimeOfBet(Date date){
        // TODO: probably should be timestamp and not modifiable
        this.dateAndTimeOfBet = date;
    }

    public User getUser() {
        return this.user;
    }
}
