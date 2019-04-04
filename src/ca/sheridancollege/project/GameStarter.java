package ca.sheridancollege.project;

import ca.sheridancollege.project.Card.Suit;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Brianna McBurney
 */
public class GameStarter {
    public ArrayList<Card> player1Hand;
    public ArrayList<Card> player2Hand;
    public ArrayList<Card> player3Hand;

    public ArrayList<Card> getPlayer1Hand() {
        return player1Hand;
    }

    public ArrayList<Card> getPlayer2Hand() {
        return player2Hand;
    }

    public ArrayList<Card> getPlayer3Hand() {
        return player3Hand;
    }
    
    /**
     * Shuffle the deck
     * @param cards 
     */
    public void shuffle(ArrayList<Card> cards) {
        Collections.shuffle(cards);
    }

    /**
     * 
     * @param numPlayers
     * @param cards ArrayList holding the deck of cards
     * @return ArrayList of the remaining cards
     */
    public ArrayList<Card> deal(int numPlayers, ArrayList<Card> cards) {
        if (numPlayers == 2) {
            for (int i = 0; i<cards.size(); i++) {
                if (0 <= i && i<7){
                    player1Hand.add(cards.get(i));                    
                } else if (7 <= i && i < 14) {
                    player2Hand.add(cards.get(i));
                }                
                cards.remove(i);
            }            
        } else if (numPlayers == 3) {
            for (int i = 0; i<cards.size(); i++) {
                if (0 <= i && i<7){
                    player1Hand.add(cards.get(i));
                } else if (7 <= i && i < 14) {
                    player2Hand.add(cards.get(i));
                }  else if (14 <= i && i < 21) {
                    player3Hand.add(cards.get(i));
                } 
                cards.remove(i);
            }
        }
        
        return cards;
    }

    public Suit createTrump(ArrayList<Card> remainingCards) {
        Suit trumpSuit = remainingCards.get(0).getSuit();
        
        return trumpSuit;
    }

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