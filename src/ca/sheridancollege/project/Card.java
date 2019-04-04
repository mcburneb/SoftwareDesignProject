package ca.sheridancollege.project;

/**
 * 
 * @author Brianna McBurney
 */
public class Card {

    public enum Value { SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE }

    public enum Suit { HEARTS, DIAMONDS, SPADES, CLUBS }

    private Suit suit;
    private Value rank;
    
    /**
     *
     * @param suit
     * @param rank
     */
    public Card(Suit suit, Value rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return this.suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public Value getValue() {
        return this.rank;
    }

    public void setValue(Value rank) {
        this.rank = rank;
    }

}
