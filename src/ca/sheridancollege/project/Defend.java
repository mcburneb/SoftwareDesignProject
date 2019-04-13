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

        do  {
            
            System.out.println(defendingPlayer.getName() + " pick a card to defend the attack ("+ attackingCard.toString() +") with or enter 0 to fold");
            for (int i=0;i<playerHand.size();i++) {
                System.out.print(playerHand.get(i).toString() + "\t");
            }
            System.out.println(" ");
            String chosenCard = in.nextLine();

            if ("0".equals(chosenCard)) {
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
                } else {
                    System.out.println("That's not a card, please try again!\n");
                }   
            }          

        } while (askAgain); 
        
        return defendingCard;
    }

    public ArrayList<Card> pickUpCards(ArrayList<Card> playerHand, ArrayList<Card> onTable) {
        
        for (int i=0;i<onTable.size();i++) {
            playerHand.add(onTable.get(i));
        }
        
        return playerHand;
    }

}
