

package ca.sheridancollege.project;

import java.util.Comparator;

/**
 *
 * @author Brianna McBurney, Artur Hrystenko, & Shaheer Syed
 */
public class CardComparatorByBothValues implements Comparator<Card>{

    /**
     * Comparing both the SUIT and VALUE of the two cards
     * 
     * @param c1
     * @param c2
     * @return 
     */
    @Override
    public int compare(Card c1, Card c2) {
        int valueCompare = 0;
        int suitCompare = 0;
        
        CardComparator comparator = new CardComparator();
        valueCompare = comparator.compare(c1, c2);
        
        if(c1.getSuit() == c2.getSuit()){
        } else {
            suitCompare = -1;
        }
        
        if(valueCompare == 0 && suitCompare == 0){
            return 0;
        } else {
            return -1;
        }
        
    }

}
