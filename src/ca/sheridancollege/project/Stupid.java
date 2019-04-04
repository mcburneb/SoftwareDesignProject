package ca.sheridancollege.project;

import ca.sheridancollege.project.Card.Suit;
import ca.sheridancollege.project.Card.Value;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Brianna McBurney
 */
public class Stupid {
    private static ArrayList<Card> player1Hand;
    private static ArrayList<Card> player2Hand;
    private static ArrayList<Card> player3Hand;
    private static Suit trump;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GameStart gameStart = new GameStart();
        
        // create the deck then shuffle it
        ArrayList<Card> deck = gameStart.createDeck();
        gameStart.shuffle(deck);
        
        System.out.println("How many players are there, 2 or 3?");
        Scanner in = new Scanner(System.in);
        int numPlayers = in.nextInt();
        
        // deal cards and get the remaining cards back
        ArrayList<Card> remainingDeck = gameStart.deal(numPlayers, deck);
        
        // get players hands after the cards have been dealt
        player1Hand = gameStart.getPlayer1Hand();
        player2Hand = gameStart.getPlayer2Hand();
        player3Hand = gameStart.getPlayer3Hand();
        
        // creating players
        Player p1 = new Player("player 1", false, player1Hand);
        Player p2 = new Player("player 2", false, player2Hand);
        Player p3 = new Player("player 3", false, player3Hand);
        
        // create the trump suit based on the first card in the remainingDeck
        trump = gameStart.createTrump(remainingDeck);
        
        Player startPlayer = lowestTrumpValue(p1, p2, p3);
        
    }
    
    public static Player lowestTrumpValue(Player player1, Player player2, Player player3){
        // make their card with the lowest value first
        Collections.sort(player1Hand, new CardComparator());
        Collections.sort(player2Hand, new CardComparator());
        Collections.sort(player3Hand, new CardComparator());
        
        Card p1Lowest = null;
        for(int i=0;i<player1Hand.size();i++) {
            Suit cardSuit = player1Hand.get(i).getSuit();
            if (cardSuit == trump) {
                p1Lowest = player1Hand.get(i);
                break;
            }
        }
        
        Card p2Lowest = null;
        for(int i=0;i<player2Hand.size();i++) {
            Suit cardSuit = player2Hand.get(i).getSuit();
            if (cardSuit == trump) {
                p1Lowest = player2Hand.get(i);
                break;
            }
        }
        
        Card p3Lowest = null;
        for(int i=0;i<player3Hand.size();i++) {
            Suit cardSuit = player3Hand.get(i).getSuit();
            if (cardSuit == trump) {
                p1Lowest = player3Hand.get(i);
                break;
            }
        }
        CardComparator cardComparator = new CardComparator();
        int compare1n2 = cardComparator.compare(p1Lowest, p2Lowest);
        
        Player returnCard = null;
        switch (compare1n2) {
            case -1:
                int compare1n3 = cardComparator.compare(p1Lowest, p3Lowest);
                switch (compare1n3) {
                    case -1:
                        returnCard = player1;
                    case 1:
                        returnCard = player2;
                        // p1 and p3 are equal
                    default: returnCard = player3;
                        break;
                }   break;
            case 1:
                int compare2n3 = cardComparator.compare(p2Lowest, p3Lowest);
                switch (compare2n3) {
                    case -1:
                        returnCard = player2;
                    case 1:
                        returnCard = player3;
                        // p2 and p3 are equal
                    default: returnCard = player3;
                        break;
                }   break;
            default:// p1 and p2 are equals
                int compare1or2n3 = cardComparator.compare(p2Lowest, p3Lowest);
                switch (compare1or2n3) {
                    case -1:
                        returnCard = player2;
                    case 1:
                        returnCard = player3;
                        // p2 and p3 are equal
                    default: returnCard = player3;
                        break;
                }   break;
        }
        
        return returnCard;
    }
}
