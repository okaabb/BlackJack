package blackjack;

public class Card {
    private final int suit;
    private final int rank;
    private final int value;

    public Card(int suit, int rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

    public Card(Card card) {
        suit = card.suit;
        rank = card.rank;
        value = card.value;
    }

    public int getRank() {
        return rank;
    }

    public int getValue() {
        return value;
    }

    public int getSuit() {
        return suit;
    }
}
