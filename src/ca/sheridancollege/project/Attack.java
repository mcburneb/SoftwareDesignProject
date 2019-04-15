package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Brianna McBurney, Artur Hrystenko, & Shaheer Syed
 */
public class Attack {

    public Card chooseCard(Player attackingPlayer, ArrayList<Card> onTable) {
        Boolean askAgain = true;
        Scanner in = new Scanner(System.in);
        Card attackingCard = null;
        ArrayList<Card> playerHand = attackingPlayer.getHand();

        do {
            System.out.println(attackingPlayer.getName() + " pick a card to attack with or enter 0 to end your turn");
            
            // display the cards the attacker has in their hand
            for (int i = 0; i < playerHand.size(); i++) {
                System.out.print(playerHand.get(i).toString() + "\t");
            }
            System.out.println(" ");
            
            // get the users input for the card they want to attack with
            String chosenCard = in.nextLine();

            // if the player has chosen to not attack, exit method
            if (chosenCard.equals("0")) {
                break;
            } else { 
                
                String[] parts = chosenCard.split(" ");
                // make sure they entered 2 words
                if (parts.length == 2) {
                    String chosenValue = parts[0];
                    String chosenSuit = parts[1];

                    Card c = new Card();
                    // check if attacker picked a card that is in their hand
                    Boolean isValidCard = c.isValidCard(chosenValue, chosenSuit, playerHand);

                    if (!isValidCard) { // card isn't in the attacker hand
                        System.out.println("That card isn't in your hand, please try again!\n");
                    } else {
                        // convert input into VALUE and SUIT enums
                        Card.Value chosenVALUE = Card.Value.valueOf(chosenValue.toUpperCase());
                        Card.Suit chosenSUIT = Card.Suit.valueOf(chosenSuit.toUpperCase());

                        // convert the chosen card into a Card
                        attackingCard = new Card(chosenSUIT, chosenVALUE);

                        // check if there are any cards on the table
                        if (!(onTable.isEmpty())) {
                            CardComparator cc = new CardComparator();

                            for (int i = 0; i < onTable.size(); i++) {
                                // compare the chosen card to the cards on the table
                                int compareValue = cc.compare(attackingCard, onTable.get(i));
                                
                                if (compareValue == 0) { // one of the cards on the table matches the chosen card
                                    // attack with the card
                                    System.out.println(attackingPlayer.getName() + " attacked with: " + chosenCard + "\n");
                                    askAgain = false;
                                }
                            }
                            if (askAgain == true) {
                                // none of the cards on the table match the chosen card
                                System.out.println("The value of the card chosen must match the value of a card on the table!\n");
                                attackingCard = null;
                            }

                        } else { // there are no cards on the table so the player can attack with any card
                            System.out.println(attackingPlayer.getName() + " attacked with: " + chosenCard + "\n");
                            askAgain = false;
                            break;
                        }
                    }
                } else { // player didn't enter 2 words
                    System.out.println("You have to enter the value of the card and the suit of the card (VALUE SUIT), please try again!\n");
                }
            }

        } while (askAgain);

        return attackingCard;
    }

    public void continueAttacking() {
        // TODO - implement Attack.continueAttacking
        throw new UnsupportedOperationException();
    }

}
