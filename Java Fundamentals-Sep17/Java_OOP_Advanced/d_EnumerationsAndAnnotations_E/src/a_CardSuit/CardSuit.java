package a_CardSuit;

public enum CardSuit {
    CLUBS, DIAMONDS, HEARTS, SPADES;

    @Override
    public String toString() {
        return "Ordinal value: "+ ordinal() + "; Name value: " + name();
    }
}
