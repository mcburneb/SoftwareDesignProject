/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sheridancollege.project;

import static ca.sheridancollege.project.Card.Suit.CLUBS;
import static ca.sheridancollege.project.Card.Value.SIX;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author AsusX455L
 */
public class CardTest {
    
    public CardTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testIsValidCard() {
        System.out.println("isValidCard");
        String chosenValue = "six";
        String chosenSuit = "hearts";
        ArrayList<Card> playerHand = null;
        Card instance = new Card();
        boolean expResult = true;
        boolean result = instance.isValidCard(chosenValue, chosenSuit, playerHand);
        assertEquals(expResult, result);
    }
    
}
