package e_CardCompareTo;

public class Card implements Comparable<Card> {

    private CardSuit suit;
    private CardRank rank;
    private int power;


    public Card(CardRank rank, CardSuit suit) {
        this.suit = suit;
        this.rank = rank;
        this.power = suit.getPower() + rank.getPower();
    }

    @Override
    public String toString(){
        return String.format("Card name: %s of %s; Card power: %d",
                this.rank,
                this.suit,
                this.power);
    }

    @Override
    public int compareTo(Card o) {
        return Integer.compare(this.power, o.power);
    }
}
