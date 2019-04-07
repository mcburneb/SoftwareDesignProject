package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Brianna McBurney
 */
public class Attack {
    
    public Card initialAttack(ArrayList<Card> playerHand) {
        Boolean askAgain = true;
        Scanner in = new Scanner(System.in);
        Card attackingCard = null;

        do  {
            System.out.println("Player 1 pick a card to attack with");
            for (int i=0;i<playerHand.size();i++) {
                System.out.print(playerHand.get(i).toString() + "\t");
            }
            System.out.println(" ");
            String chosenCard = in.nextLine();

            String[] parts = chosenCard.split(" ");
            if (parts.length == 2) {
                String chosenValue = parts[0];
                String chosenSuit = parts[1];

                // check if user picked a valid card
                Card c = new Card();
                Boolean isValidCard = c.isValidCard(chosenValue, chosenSuit, playerHand);

                if (!isValidCard) {
                    System.out.println("Invalid card please try again!\n");
                } else {
                    System.out.println("Player 1 attacked with: " + chosenCard + "\n");

                    // convert input into VALUE and SUIT enums
                    Card.Value chosenVALUE = Card.Value.valueOf(chosenValue.toUpperCase());
                    Card.Suit chosenSUIT = Card.Suit.valueOf(chosenSuit.toUpperCase());

                    attackingCard = new Card(chosenSUIT, chosenVALUE);

                    askAgain = false;
                }
            } else {
                System.out.println("Invalid card please try again!\n");
            }               

        } while (askAgain); 
        
        return attackingCard;
    }

    public Card chooseCard(ArrayList<Card> playerHand, ArrayList<Card> onTable) {
        Boolean askAgain = true;
        Scanner in = new Scanner(System.in);
        Card attackingCard = null;

        do  {
            System.out.println("Player 1 pick a card to attack with or enter 0 to end your turn");
            for (int i=0;i<playerHand.size();i++) {
                System.out.print(playerHand.get(i).toString() + "\t");
            }
            System.out.println(" ");
            String chosenCard = in.nextLine();

            if (chosenCard.equals("0")) {
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

                        attackingCard = new Card(chosenSUIT, chosenVALUE);

                        CardComparator cc = new CardComparator();
                        
                        for (int i=0;i<onTable.size();i++) {
                            int compareValue = cc.compare(attackingCard, onTable.get(i));
                            // make sure the value of the chosen card is creater than that of the attacking card
                            if (compareValue == 0) {
                                System.out.println("Player 1 attacked with: " + chosenCard +"\n");
                                askAgain = false;
                            } else {
                                System.out.println("The value of the card chosen must match the value of a card on the table!\n");
                            }
                        }
                    }
                } else {
                    System.out.println("That's not a card, please try again!\n");
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
