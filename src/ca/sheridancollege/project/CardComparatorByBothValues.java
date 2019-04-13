

package ca.sheridancollege.project;

import java.util.Comparator;

/**
 *
 * @author artur
 */
public class CardComparatorByBothValues implements Comparator<Card>{

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
