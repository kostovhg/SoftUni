package g_DecOfCards;

public class Card implements Comparable<Card> {

    private Suit suit;
    private Rank rank;
    private int power;


    Card(Rank rank, Suit suit) {
        this.suit = suit;
        this.rank = rank;
        this.power = suit.getPower() + rank.getPower();
    }

    /*@Override
    public String toString(){
        return String.format("Card name: %s of %s; Card power: %d",
                this.rank,
                this.suit,
                this.power);
    }*/

    @Override
    public String toString(){
        return String.format("%s of %s",
                this.rank,
                this.suit);
    }

    @Override
    public int compareTo(Card o) {
        return Integer.compare(this.power, o.power);
    }
}
