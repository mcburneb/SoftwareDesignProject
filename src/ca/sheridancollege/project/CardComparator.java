package ca.sheridancollege.project;

import java.util.Comparator;

/**
 *
 * @author Brianna McBurney
 */
public class CardComparator implements Comparator<Card>{
    
    @Override
    public int compare(Card card1, Card card2) {
        int card1Value = 0;
        switch (card1.getValue()) {
            case SIX: card1Value = 6;
                break;
            case SEVEN: card1Value = 7;
                break;
            case EIGHT: card1Value = 8;
                break;
            case NINE: card1Value = 9;
                break;
            case TEN: card1Value = 10;
                break;
            case JACK: card1Value = 11;
                break; 
            case QUEEN: card1Value = 12;
                break; 
            case KING: card1Value = 13;
                break;
            case ACE: card1Value = 14;
                break;
        }
        
        int card2Value = 0;
        switch (card2.getValue()) {
            case SIX: card2Value = 6;
                break;
            case SEVEN: card2Value = 7;
                break;
            case EIGHT: card2Value = 8;
                break;
            case NINE: card2Value = 9;
                break;
            case TEN: card2Value = 10;
                break;
            case JACK: card2Value = 11;
                break; 
            case QUEEN: card2Value = 12;
                break; 
            case KING: card2Value = 13;
                break;
            case ACE: card2Value = 14;
                break;
        }
        
        int returnValue = 0;
        if (card1Value > card2Value){
            returnValue = 1;
        } else if (card1Value < card2Value) {
            returnValue = -1;
        }
        
        return returnValue;
    }
}