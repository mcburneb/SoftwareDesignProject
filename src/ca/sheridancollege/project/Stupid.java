package ca.sheridancollege.project;

import ca.sheridancollege.project.Card.Suit;
import java.util.ArrayList;

/**
 *
 * @author Brianna McBurney, Artur Hrystenko, & Shaheer Syed
 */
public class Stupid {

    private static ArrayList<Card> player1hand;
    private static ArrayList<Card> player2hand;

    private static ArrayList<Card> remainingDeck;
    private static ArrayList<Card> onTable;

    private static ArrayList<Player> players;
    private static Suit trump;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GameStarter gameStart = new GameStarter();
        DiscardPile discard = new DiscardPile();

        // create the deck then shuffle it
        ArrayList<Card> deck = gameStart.createDeck();
        gameStart.shuffle(deck);

        // deal cards and get the remaining cards back
        remainingDeck = gameStart.deal(2, deck);

        // initalize onTable ArrayList
        onTable = new ArrayList<>();

        // get players hands after the cards have been dealt
        player1hand = gameStart.getPlayer1hand();
        player2hand = gameStart.getPlayer2hand();

        // creating players
        Player p1 = new Player("player 1", player1hand);
        Player p2 = new Player("player 2", player2hand);
        players = new ArrayList<>();
        players.add(p1);
        players.add(p2);

        // create the trump suit based on the first card in the remainingDeck
        trump = gameStart.createTrump(remainingDeck);
        System.out.println("The trump suit is " + trump);

        boolean endTurn = false;
        int attacker = 0;
        int defender = 1;
        
        // while both players have cards in their hands keep playing
        while(players.get(0).getHand().size() > 0 && players.get(1).getHand().size() > 0) {
            while (!endTurn) {
                Attack a = new Attack();
                
                // get the player to pick the card they want to attack with
                Card attackingCard = a.chooseCard(players.get(attacker), onTable);
                try {
                    if (attackingCard == null) { // player has decided not to attack
                        System.out.println(players.get(defender).getName() + " decided not to attack.");
                        
                        //add cards from table to discard
                        for (int k = 0; k < onTable.size(); k++) {
                            discard.addToDiscard(onTable.get(k));
                        }
                        System.out.println("The cards on the table have been discarded.\n");

                        break;
                    } else { 
                        
                        // add the card the player picked to the table
                        onTable.add(attackingCard);

                        CardComparatorByBothValues comparator = new CardComparatorByBothValues();
                        // find the card the player last played and remove it from their hand
                        for (int i = 0; i < players.get(attacker).getHand().size(); i++) {
                            int matches = comparator.compare(players.get(attacker).getHand().get(i), onTable.get(onTable.size() - 1));
                            if (matches == 0) {
                                players.get(attacker).getHand().remove(i);
                            }
                        }

                    }
                } catch (NullPointerException ex) {
                    System.out.println(ex.getMessage());
                }

                Defend d = new Defend();
                // get the player to pick the card they want to defned with
                Card defendingCard = d.chooseCard(players.get(defender), attackingCard, trump);
                try {
                    if (defendingCard == null) { // player decided to fold                       
                        
                        // take the cards from the table and put them in the defendors hand
                        players.get(defender).setHand(d.pickUpCards(players.get(defender).getHand(), onTable));
                        System.out.println(players.get(defender).getName() + " picked up the cards on the table.");
                        
                        break;                    
                    } else {                        
                        // add the card the player defended with to the table
                        onTable.add(defendingCard);

                        CardComparatorByBothValues comparator = new CardComparatorByBothValues();
                        // find the card the player last played and remove it from their hand
                        for (int i = 0; i < players.get(defender).getHand().size(); i++) {
                            int matches = comparator.compare(players.get(defender).getHand().get(i), onTable.get(onTable.size() - 1));
                            if (matches == 0) {
                                players.get(defender).getHand().remove(i);
                            }
                        }

                        // show cards on the table
                        System.out.println("Cards on the table: ");
                        for (int j = 0; j < onTable.size(); j++) {
                            System.out.print(onTable.get(j).getValue() + " " + onTable.get(j).getSuit() + "\t");
                        }
                        System.out.println("");

                    }
                } catch (NullPointerException ex) {
                    System.out.println(ex.getMessage());
                }

            }

            // show all the cards that are being discarded
            discard.showLastDiscarded(onTable);

            // clear the table 
            onTable.clear();            
            
            // make sure the attacking and defending players have at least 6 cards
            replenishHand();
            System.out.println("\n** END OF TURN **\n");
            
            // remind the players of what the trump suit is
            System.out.println("The trump suit is " + trump);
            
            // change turns
            if (attacker == 0) {
                attacker = 1;
                defender = 0;
            } else {
                attacker = 0;
                defender = 1;
            }
        }

    }

    /**
     * Make sure players have 6 cards in their hand after a turn is over
     * Unless the deck is empty then do nothing
     */
    private static void replenishHand() {
        // make sure there are cards in the deck
        if (remainingDeck.size() > 0) {
            
            // if the players hand has less than 6 cards, add a card
            while (players.get(0).getHand().size() < 6) {
                // get new card from 'top' of the deck
                player1hand.add(remainingDeck.get(0));

                // remove card from remainingDeck
                remainingDeck.remove(0);
            }

            // if the players hand has less than 6 cards, add a card
            while (players.get(1).getHand().size() < 6) {
                // get new card from 'top' of the deck
                player2hand.add(remainingDeck.get(0));

                // remove card from remainingDeck
                remainingDeck.remove(0);
            }
        }
    }
}
