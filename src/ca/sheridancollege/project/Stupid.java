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
    private static ArrayList<Card> discarded;
    
    private static ArrayList<Player> players;
    private static Suit trump;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GameStarter gameStart = new GameStarter();
        
        // create the deck then shuffle it
        ArrayList<Card> deck = gameStart.createDeck();
        gameStart.shuffle(deck);
        
//        System.out.println("How many players are there, 2 or 3?");
//        Scanner in = new Scanner(System.in);
//        int numPlayers = in.nextInt();
        
        // deal cards and get the remaining cards back
        remainingDeck = gameStart.deal(3, deck);
        
        // initalize onTable ArrayList
        onTable = new ArrayList<>();
        
        // get players hands after the cards have been dealt
        player1hand = gameStart.getPlayer1hand();
        player2hand = gameStart.getPlayer2hand();
        player3hand = gameStart.getPlayer3hand();
        
        // creating players
        Player p1 = new Player("player 1", false, player1hand);
        Player p2 = new Player("player 2", false, player2hand);
        Player p3 = new Player("player 3", false, player3hand);
        
        // create the trump suit based on the first card in the remainingDeck
        trump = gameStart.createTrump(remainingDeck);
        
        System.out.println("The trump suit is " + trump);
        
        boolean endTurn = false;
        Scanner in = new Scanner(System.in);
        
        
        
        // inital attack
        Attack a = new Attack();
        Card attackingCard = a.initialAttack(player1hand);
        
        // TO DO: remove attackingCard from players hand

        // put attack card 'on' the table
        onTable.add(attackingCard);
        
        // player 1 is attacking
        while (!endTurn) {
            Defend d = new Defend();
            Card defendingCard = d.chooseCard(player2hand, attackingCard, trump);
            try {
                // 'put' defending card on the table
                onTable.add(defendingCard);
                
                // TO DO: remove defendingCard from players hand
                
                // show cards on the table
                System.out.println("Cards on the table: ");
                for (int i=0;i<onTable.size();i++) {
                    System.out.print(onTable.get(i).getValue() + " " + onTable.get(i).getSuit() + "\t");
                }
            } catch (NullPointerException ex){
                // player decided to fold
                // get updated hand with cards from the table
                player2hand = d.pickUpCards(player2hand, onTable);
                
                // clear cards from table
                onTable.clear();
                
                System.out.println("END OF TURN");
                break;
            }
            
            attackingCard = a.chooseCard(player1hand, onTable);
            try {
                onTable.add(attackingCard);
                
                // TO DO: remove attackingCard from players hand
                
            } catch (NullPointerException ex){
                // player decided to not attack anymore
                discardCards();
            }
            
            // make sure the attacking and defending players have at least 6 cards
            replenishHand();
            
        }
        
    }
    
    public static Player lowestTrumpValue(Player player1, Player player2, Player player3){
        // make their card with the lowest value first
        Collections.sort(player1hand, new CardComparator());
        Collections.sort(player2hand, new CardComparator());
        Collections.sort(player3hand, new CardComparator());
        
        Card p1Lowest = null;
        for(int i=0;i<player1hand.size();i++) {
            Suit cardSuit = player1hand.get(i).getSuit();
            if (cardSuit == trump) {
                p1Lowest = player1hand.get(i);
                break;
            }
        }
        
        Card p2Lowest = null;
        for(int i=0;i<player2hand.size();i++) {
            Suit cardSuit = player2hand.get(i).getSuit();
            if (cardSuit == trump) {
                p1Lowest = player2hand.get(i);
                break;
            }
        }
        
        Card p3Lowest = null;
        for(int i=0;i<player3hand.size();i++) {
            Suit cardSuit = player3hand.get(i).getSuit();
            if (cardSuit == trump) {
                p1Lowest = player3hand.get(i);
                break;
            }
        }
        CardComparator cardComparator = new CardComparator();
        int compare1n2 = cardComparator.compare(p1Lowest, p2Lowest);
        
        Player returnPlayer = null;
        switch (compare1n2) {
            case 1: // p2 has lower value
                int compare1n3 = cardComparator.compare(p1Lowest, p3Lowest);
                switch (compare1n3) {
                    case -1: // p1 has lower value
                        returnPlayer = player1;
                        break;
                    case 1: // p3 has lower value
                        returnPlayer = player2;
                        break;
                }   break;
            case -1: // p1 has smaller value 
                int compare2n3 = cardComparator.compare(p2Lowest, p3Lowest);
                switch (compare2n3) {
                    case 1: // p2 has lower value
                        returnPlayer = player2;
                        break;
                    case -1: // p3 has lower value
                        returnPlayer = player3;
                        break;
                }   break;
        }
        
        return returnPlayer;
    }
    
    public static void discardCards() {
        for(int i=0;i<onTable.size();i++) {
            discarded.add(onTable.get(i));
        }
        onTable.clear();
    }

    private static void replenishHand() {
        if(remainingDeck.size() > 0) {
            while (player1hand.size() < 6) {
                // get new card from 'top' of the deck
                player1hand.add(remainingDeck.get(0));
                
                // remove card from remainingDeck
                remainingDeck.remove(0);
            }
            
            while (player2hand.size() < 6) {
                // get new card from 'top' of the deck
                player2hand.add(remainingDeck.get(0));
                
                // remove card from remainingDeck
                remainingDeck.remove(0);
            }
            
            while (player3hand.size() < 6) {
                // get new card from 'top' of the deck
                player3hand.add(remainingDeck.get(0));
                
                // remove card from remainingDeck
                remainingDeck.remove(0);
            }
        }
    }
}
