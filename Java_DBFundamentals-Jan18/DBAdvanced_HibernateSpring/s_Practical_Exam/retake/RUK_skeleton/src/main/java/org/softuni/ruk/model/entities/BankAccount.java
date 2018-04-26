package org.softuni.ruk.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bank_accounts")
public class BankAccount {

    private Integer id; //– integer number, primary identification field.
    private String accountNumber; //– a string (required).
    private BigDecimal balance; //– a decimal data type.
    private Client client; //– a Client entity (One).
    private List<Card> cards; // – a collection of Card entity.

    public BankAccount() {
        this.cards = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "account_number", nullable = false)
    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Column(name = "balance", precision = 10, scale = 2)
    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @OneToOne(fetch=FetchType.EAGER, mappedBy="bankAccount", cascade = CascadeType.ALL)
    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @OneToMany(mappedBy="bankAccount", cascade=CascadeType.ALL, orphanRemoval=true)
    public List<Card> getCards() {
        return this.cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
