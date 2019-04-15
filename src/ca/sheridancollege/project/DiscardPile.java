

package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 *
 * @author Brianna McBurney, Artur Hrystenko, & Shaheer Syed
 */
public class DiscardPile {
    public static ArrayList<Card> discardPile;

    public DiscardPile() {
        this.discardPile = new ArrayList<Card>();
    }

    /**
     * Add a card to the discard pile
     * 
     * @param card The card to add to the discard pile
     */
    public void addToDiscard(Card card){
        discardPile.add(card);
    }
    
    /**
     * Remove all cards from the discard pile
     */
    public void clearDiscard(){
        discardPile.clear();
    }
    
    /**
     * Display all the cards in the discard pile
     */
    public void showDiscard(){
        for(int i = 0; i < discardPile.size(); i++){
            System.out.println(discardPile.get(i).toString());
        }
    }
    /**
     * Show the cards that have been played that turn and are going to be discarded
     * 
     * @param onTable 
     */
    public void showLastDiscarded(ArrayList<Card> onTable){
        if (onTable.size() > 0){
            if (discardPile.size() > 0) {            
                System.out.println("Cards Discarded:   ");            
                // only show the cards that were played in the last turn
                for (int i = (discardPile.size() - 1); i >= (discardPile.size() - onTable.size()); i--){
                    System.out.println(discardPile.get(i).toString() + ", ");
                }
            }
        }
    }
}
 