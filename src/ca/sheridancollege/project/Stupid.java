package ca.sheridancollege.project;

import ca.sheridancollege.project.Card.Suit;
import ca.sheridancollege.project.Card.Value;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Brianna McBurney
 */
public class Stupid {
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
        ArrayList<Card> player1Hand = gameStart.getPlayer1Hand();
        ArrayList<Card> player2Hand = gameStart.getPlayer2Hand();
        ArrayList<Card> player3Hand = gameStart.getPlayer3Hand();
        
        // creating players
        Player p1 = new Player("player 1", false, player1Hand);
        Player p2 = new Player("player 2", false, player2Hand);
        Player p3 = new Player("player 3", false, player3Hand);
        
        // create the trump suit based on the first card in the remainingDeck
        Suit trump = gameStart.createTrump(remainingDeck);
        
        
        // ******** PROBLEM
        // cant compare enums cause they are words not the actual values
        
    }
}
