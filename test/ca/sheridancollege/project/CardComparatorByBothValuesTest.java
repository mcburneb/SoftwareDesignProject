/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sheridancollege.project;

import static ca.sheridancollege.project.Card.Suit.CLUBS;
import static ca.sheridancollege.project.Card.Suit.HEARTS;
import static ca.sheridancollege.project.Card.Value.ACE;
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
public class CardComparatorByBothValuesTest {
    
    public CardComparatorByBothValuesTest() {
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
    public void GoodtestCompare() {
        System.out.println("compare");
        Card c1 = new Card(CLUBS, ACE);
        Card c2 = new Card(CLUBS, ACE);
        CardComparatorByBothValues instance = new CardComparatorByBothValues();
        int expResult = 0;
        int result = instance.compare(c1, c2);
        assertEquals(expResult, result);
    }
    @Test
    public void BoundrytestCompare() {
        System.out.println("compare");
        Card c1 = new Card(CLUBS, SIX);
        Card c2 = new Card(CLUBS, ACE);
        CardComparatorByBothValues instance = new CardComparatorByBothValues();
        int expResult = -1;
        int result = instance.compare(c1, c2);
        assertEquals(expResult, result);
    }
    @Test
    public void BadtestCompare() {
        System.out.println("compare");
        Card c1 = new Card(HEARTS, SIX);
        Card c2 = new Card(CLUBS, SIX);
        CardComparatorByBothValues instance = new CardComparatorByBothValues();
        int expResult = -1;
        int result = instance.compare(c1, c2);
        assertEquals(expResult, result);
    }
    
}
