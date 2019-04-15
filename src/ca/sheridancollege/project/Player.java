package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 *
 * @author Brianna McBurney, Artur Hrystenko, & Shaheer Syed
 */
public class Player {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public boolean isIsAttacking() {
        return isAttacking;
    }

    public void setIsAttacking(boolean isAttacking) {
        this.isAttacking = isAttacking;
    }

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

//    /**
//     *
//     * @param isAttacking
//     */
//    public void attack(int isAttacking) {
//        // TODO - implement Player.attack
//        throw new UnsupportedOperationException();
//    }
//
//    /**
//     *
//     * @param isAttacking
//     */
//    public void defend(int isAttacking) {
//        // TODO - implement Player.defend
//        throw new UnsupportedOperationException();
//    }

    /**
     * If a card in the players had matches a card on the table, remove that card
     * 
     * @param cardsOnTable The cards that have been played that turn
     * @param player The current player
     */
    public void removeFromHand(ArrayList<Card> cardsOnTable, Player player) {
        CardComparatorByBothValues comp = new CardComparatorByBothValues();
        Card cardToRemove = cardsOnTable.get(cardsOnTable.size() - 1);
        for (int i = 0; i < player.getHand().size(); i++) {
            if ((comp.compare(player.getHand().get(i), cardToRemove)) == 0) {
                player.getHand().remove(i);
            }
        }
    }

}
