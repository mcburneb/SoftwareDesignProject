package ca.sheridancollege.project;

import ca.sheridancollege.project.Card.Suit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Brianna McBurney
 */
public class Stupid {

    private static ArrayList<Card> player1hand;
    private static ArrayList<Card> player2hand;
    private static ArrayList<Card> player3hand;

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
        Player p1 = new Player("player 1", false, player1hand);
        Player p2 = new Player("player 2", false, player2hand);
        players = new ArrayList<>();
        players.add(p1);
        players.add(p2);

        // create the trump suit based on the first card in the remainingDeck
        trump = gameStart.createTrump(remainingDeck);

        System.out.println("The trump suit is " + trump);

        boolean endTurn = false;
        int attacker = 0;
        int defender = 1;
        
        while(players.get(0).getHand().size() > 0 && players.get(1).getHand().size() > 0) {
            // player 1 is attacking
            while (!endTurn) {
                Attack a = new Attack();
                Card attackingCard = a.chooseCard(players.get(attacker), onTable);
                try {
                    if (attackingCard == null) {
                        //add cards from table to discard
                        for (int k = 0; k < onTable.size(); k++) {
                            discard.addToDiscard(onTable.get(k));
                        }

                        break;
                    } else {
                        onTable.add(attackingCard);

                        CardComparatorByBothValues comparator = new CardComparatorByBothValues();
                        // find the card the player last played and remove it from their hand
                        for (int f = 0; f < players.get(attacker).getHand().size(); f++) {
                            int matches = comparator.compare(players.get(attacker).getHand().get(f), onTable.get(onTable.size() - 1));
                            if (matches == 0) {
                                players.get(attacker).getHand().remove(f);
                            }
                        }

                    }
                } catch (NullPointerException ex) {
                    // player decided to not attack anymore

                }

                Defend d = new Defend();
                Card defendingCard = d.chooseCard(players.get(defender), attackingCard, trump);
                try {
                    if (defendingCard != null) {

                        // 'put' defending card on the table
                        onTable.add(defendingCard);

                        CardComparatorByBothValues comparator = new CardComparatorByBothValues();
                        // find the card the player last played and remove it from their hand
                        for (int f = 0; f < players.get(defender).getHand().size(); f++) {
                            int matches = comparator.compare(players.get(defender).getHand().get(f), onTable.get(onTable.size() - 1));
                            if (matches == 0) {
                                players.get(defender).getHand().remove(f);
                            }
                        }

                        // show cards on the table
                        System.out.println("Cards on the table: ");
                        for (int j = 0; j < onTable.size(); j++) {
                            System.out.print(onTable.get(j).getValue() + " " + onTable.get(j).getSuit() + "\t");
                        }
                        System.out.println("");
                    } else {
                        // player decided to fold
                        // get updated hand with cards from the table

                        players.get(defender).setHand(d.pickUpCards(players.get(defender).getHand(), onTable));
                        break;
                        // move cards from table to discard pile

                    }
                } catch (NullPointerException ex) {
                    System.out.println("oops");
                }

            }

            System.out.println("Cards Discarded:   ");
            // make sure the attacking and defending players have at least 6 cards
            discard.showLastDiscarded(onTable.size());

            onTable.clear();
            replenishHand();
            System.out.println("End of the turn");
            
            if (attacker == 0) {
                attacker = 1;
                defender = 0;
            } else {
                attacker = 0;
                defender = 1;
            }
            System.out.println("The trump suit is " + trump);
        }

    }

    private static void replenishHand() {
        if (remainingDeck.size() > 0) {
            while (players.get(0).getHand().size() < 6) {
                // get new card from 'top' of the deck
                player1hand.add(remainingDeck.get(0));

                // remove card from remainingDeck
                remainingDeck.remove(0);
            }

            while (players.get(1).getHand().size() < 6) {
                // get new card from 'top' of the deck
                player2hand.add(remainingDeck.get(0));

                // remove card from remainingDeck
                remainingDeck.remove(0);
            }
        }
    }
}
