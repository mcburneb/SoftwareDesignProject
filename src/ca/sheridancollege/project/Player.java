package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 * 
 * @author Briana McBurney
 */
public class Player {

    private String name;
    private ArrayList<Card> hand;
    private boolean isAttacking;

    /**
     *
     * @param name
     * @param isAttacking
     * @param hand
     */
    public Player(String name, boolean isAttacking, ArrayList<Card> hand) {
        this.name = name;
        this.isAttacking = isAttacking;
        this.hand = hand;
    }

    /**
     *
     * @param isAttacking
     */
    public void attack(int isAttacking) {
        // TODO - implement Player.attack
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param isAttacking
     */
    public void defend(int isAttacking) {
        // TODO - implement Player.defend
        throw new UnsupportedOperationException();
    }

}
