package b_CardRank;

public enum CardRank {
    ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;


    @Override
    public String toString() {
        return "Ordinal value: "+ ordinal() + "; Name value: " + name();
    }
}
