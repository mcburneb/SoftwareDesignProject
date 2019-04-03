package ca.sheridancollege.project;

import ca.sheridancollege.project.Card.Suit;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Brianna McBurney
 */
public class gameStart {
    private ArrayList<Card> player1;
    private ArrayList<Card> player2;
    private ArrayList<Card> player3;

    public ArrayList<Card> getPlayer1() {
        return player1;
    }

    public ArrayList<Card> getPlayer2() {
        return player2;
    }

    public ArrayList<Card> getPlayer3() {
        return player3;
    }
    
    /**
     * Shuffle the deck
     * @param cards 
     */
    public void shuffle(ArrayList cards) {
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
                    player1.add(cards.get(i));                    
                } else if (7 <= i && i < 14) {
                    player2.add(cards.get(i));
                }                
                cards.remove(i);
            }            
        } else if (numPlayers == 3) {
            for (int i = 0; i<cards.size(); i++) {
                if (0 <= i && i<7){
                    player1.add(cards.get(i));
                } else if (7 <= i && i < 14) {
                    player2.add(cards.get(i));
                }  else if (14 <= i && i < 21) {
                    player3.add(cards.get(i));
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

    public ArrayList createDeck() {
        ArrayList<Card> cards = new ArrayList<>();
        
        for (Card.Suit s : Card.Suit.values()) {
            for (Card.Rank v : Card.Rank.values()) {
                cards.add(new Card(s, v));
            }
        }
        
        return cards;
    }
}
