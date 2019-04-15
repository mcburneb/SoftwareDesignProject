package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 * 
 * @author Brianna McBurney, Artur Hrystenko, & Shaheer Syed
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
    
    public Card(){
        
    };

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

    /**
     * Make sure the card the player has chosen to play is in their hand
     * 
     * @param chosenValue The value the player entered
     * @param chosenSuit The suit the player entered
     * @param playerHand The ArrayList that holds the player's hand
     * @return True if the card is in the player's hand, False if it doesn't
     */
    public boolean isValidCard(String chosenValue, String chosenSuit, ArrayList<Card> playerHand) {
        Boolean returnValue = false;
        try {
            // convert the input for suit and value to SUIT and VALUE
            Value chosenVALUE = Value.valueOf(chosenValue.toUpperCase());
            Suit chosenSUIT = Suit.valueOf(chosenSuit.toUpperCase());

            // comapre each card in the players hand to the chosen card
            for (int i=0;i<playerHand.size();i++) {
                if (chosenVALUE == playerHand.get(i).getValue() && chosenSUIT == playerHand.get(i).getSuit()) {
                    returnValue = true;
                }
                
            }
        } catch (IllegalArgumentException ex) {
            returnValue = false;
        }
        
        return returnValue;
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        String cardString = this.getValue() + " " + this.getSuit();
        
        return cardString;
    }
}
