package org.softuni.ruk.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "cards")
public class Card {

    private Integer id; //– integer number, primary identification field.
    private String cardNumber; //– a string (required).
    private String cardStatus; //– a string (required).
    private BankAccount bankAccount; //– a Bank Account entity.

    public Card() {
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "card_number", nullable = false)
    public String getCardNumber() {
        return this.cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Column(name = "card_status", nullable = false)
    public String getCardStatus() {
        return this.cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="bank_account", foreignKey=@ForeignKey(name = "fk_card_bank_account"))
    public BankAccount getBankAccount() {
        return this.bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}
