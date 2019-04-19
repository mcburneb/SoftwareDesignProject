package ca.sheridancollege.project;

import ca.sheridancollege.project.Card.Suit;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Brianna McBurney, Artur Hrystenko, & Shaheer Syed
 */
public class GameStarter {
    public ArrayList<Card> player1hand;
    public ArrayList<Card> player2hand;

    public ArrayList<Card> getPlayer1hand() {
        return player1hand;
    }

    public ArrayList<Card> getPlayer2hand() {
        return player2hand;
    }
    
    /**
     * Shuffle the deck
     * @param cards 
     */
    public void shuffle(ArrayList<Card> cards) {
        Collections.shuffle(cards);
    }

    /**
     * Give each player 6 cards
     * 
     * @param numPlayers
     * @param cards ArrayList holding the deck of cards
     * @return ArrayList of the remaining cards
     */
    public ArrayList<Card> deal(int numPlayers, ArrayList<Card> cards) {
        player1hand = new ArrayList<>();
        player2hand = new ArrayList<>();
        if (numPlayers == 2) {
            for (int i = 0; i<cards.size(); i++) {
                if (0 <= i && i<6){
                    player1hand.add(cards.get(i));                    
                } else if (6 <= i && i < 12) {
                    player2hand.add(cards.get(i));
                }                
                cards.remove(i);
            }            
        }
        
        return cards;
    }

    /**
     * Create the trump suit by getting the SUIT of the card on top of the pile
     * 
     * @param remainingCards
     * @return The trump SUIT
     */
    public Suit createTrump(ArrayList<Card> remainingCards) {
        Suit trumpSuit = remainingCards.get(0).getSuit();
        
        return trumpSuit;
    }

    /**
     * Create the deck of cards that will be used
     * 
     * @return 
     */
    public ArrayList<Card> createDeck() {
        ArrayList<Card> cards = new ArrayList<>();
        
        for (Card.Suit s : Card.Suit.values()) {
            for (Card.Value v : Card.Value.values()) {
                cards.add(new Card(s, v));
            }
        }
        
        return cards;
    }
}