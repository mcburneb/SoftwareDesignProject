package ca.sheridancollege.project;

import ca.sheridancollege.project.Card.Suit;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Brianna McBurney, Artur Hrystenko, & Shaheer Syed
 */
public class Defend {

    public Card chooseCard(Player defendingPlayer, Card attackingCard, Suit trump) {
        Boolean askAgain = true;
        Scanner in = new Scanner(System.in);
        Card defendingCard = null;
        ArrayList<Card> playerHand = defendingPlayer.getHand();

        do {
            System.out.println(defendingPlayer.getName() + " pick a card to defend the attack ("+ attackingCard.toString() +") with or enter 0 to fold");
            
            // display the cards in the defendors hand
            for (int i=0;i<playerHand.size();i++) {
                System.out.print(playerHand.get(i).toString() + "\t");
            }
            System.out.println(" ");
            
            // get the players input for the card they want to defend with
            String chosenCard = in.nextLine();

            if (chosenCard.equals("0")) { // user has decided to not defend and fold
                break;
            } else {
                String[] parts = chosenCard.split(" ");
                // make sure they entered 2 words
                if (parts.length == 2) {
                    String chosenValue = parts[0];
                    String chosenSuit = parts[1];

                    // check if user picked a valid card
                    Card c = new Card();
                    Boolean isValidCard = c.isValidCard(chosenValue, chosenSuit, playerHand);

                    if (!isValidCard) {
                        System.out.println("That card isn't in your hand, please try again!\n");
                    } else {
                        // convert input into VALUE and SUIT enums
                        Card.Value chosenVALUE = Card.Value.valueOf(chosenValue.toUpperCase());
                        Card.Suit chosenSUIT = Card.Suit.valueOf(chosenSuit.toUpperCase());

                        defendingCard = new Card(chosenSUIT, chosenVALUE);

                        if (chosenSUIT == attackingCard.getSuit()) {
                            CardComparator cc = new CardComparator();

                            int compareValue = cc.compare(defendingCard, attackingCard);
                            // make sure the value of the chosen card is creater than that of the attacking card
                            if (compareValue == 1) {
                                System.out.println(defendingPlayer.getName() + " defended the attack with " + defendingCard.toString() + "\n");
                                askAgain = false;
                                
                            } else {
                                System.out.println("That won't defend against the attacking card!\n");
                            }
                        } else if(chosenSUIT == trump) {
                            // trump beats the attacking card no matter what the values are
                            System.out.println(defendingPlayer.getName() + " defended the attack with " + defendingCard.toString() + "\n");
                            askAgain = false;
                            
                            
                        } else {
                            System.out.println("The suits have to match!\n");
                        }
                    }
                } else {  // player didn't enter 2 words
                    System.out.println("You have to enter the value of the card and the suit of the card (VALUE SUIT), please try again!\n");
                }   
            }          

        } while (askAgain); 
        
        return defendingCard;
    }

    /**
     * Take the cards on the table and add them to the players hand.
     * 
     * @param playerHand
     * @param onTable
     * @return The players hand with the cards from the table
     */
    public ArrayList<Card> pickUpCards(ArrayList<Card> playerHand, ArrayList<Card> onTable) {
        
        for (int i=0;i<onTable.size();i++) {
            playerHand.add(onTable.get(i));
        }
        
        return playerHand;
    }

}
