

package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 *
 * @author artur
 */
public class DiscardPile {
    public static ArrayList<Card> discardPile;

    public DiscardPile() {
        this.discardPile = new ArrayList<Card>();
    }

    public void addToDiscard(Card card){
        discardPile.add(card);
    }
    
    
    public void clearDiscard(){
        discardPile.clear();
    }
    
    public void showDiscard(){
        for(int i = 0; i < discardPile.size(); i++){
            System.out.println(discardPile.get(i).getValue() + "  " + discardPile.get(i).getSuit());
        }
    }
    //dont try understanding this
    public void showLastDiscarded(int cardsPlayed){
        if (discardPile.size() > 0) {
            for (int i = (discardPile.size() - 1); i >= (discardPile.size() - cardsPlayed); i--){
                System.out.println(discardPile.get(i).getValue() + "  " + discardPile.get(i).getSuit() + ", ");
            }
        }
    }
}
 