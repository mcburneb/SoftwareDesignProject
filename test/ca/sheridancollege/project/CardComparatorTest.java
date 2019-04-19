/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sheridancollege.project;

import static ca.sheridancollege.project.Card.Suit.CLUBS;
import static ca.sheridancollege.project.Card.Value.ACE;
import static ca.sheridancollege.project.Card.Value.SEVEN;
import static ca.sheridancollege.project.Card.Value.SIX;
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
public class CardComparatorTest {
    
    public CardComparatorTest() {
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
    public void badtestCompare() {
        System.out.println("compare");
        Card card1 = new Card(CLUBS, SIX);
        Card card2 = new Card(CLUBS, ACE);
        CardComparator instance = new CardComparator();
        int expResult = -1;
        int result = instance.compare(card1, card2);
        assertEquals(expResult, result);
    }
    @Test
    public void goodtestCompare() {
        System.out.println("compare");
        Card card1 = new Card(CLUBS, SEVEN);
        Card card2 = new Card(CLUBS, SIX);
        CardComparator instance = new CardComparator();
        int expResult = 1;
        int result = instance.compare(card1, card2);
        assertEquals(expResult, result);
    }
    @Test
    public void BoundrytestCompare() {
        System.out.println("compare");
        Card card1 = new Card(CLUBS, ACE);
        Card card2 = new Card(CLUBS, SIX);
        CardComparator instance = new CardComparator();
        int expResult = 1;
        int result = instance.compare(card1, card2);
        assertEquals(expResult, result);
    }
    
}
