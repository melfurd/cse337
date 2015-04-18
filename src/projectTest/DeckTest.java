package projectTest;
import java.util.*;

import project.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DeckTest {

	Deck deck = new Deck();
	@Before
	public void setUp() throws Exception {
		deck = new Deck();
	}

	/**
	 * Test to make sure there is a standard fill with size 52.
	 */
	@Test
	public void testStandardFill() {
		deck.standardFill();
		assertEquals(52, deck.size());
	}
	
	/**
	 * Tests to make sure it deals the amount of cards given 
	 */
	@Test
	public void testDealHands(){
		deck.standardFill();
		Deck returnDeck = deck.dealHand(5);
		assertEquals(5, returnDeck.size());
	}
	
	@Test
	public void testDrawCard(){
		
	}
	
	/**
	 * This deals a 2 hands  n number of cards
	 */
	@Test
	public void testDeal2Hands(){
		deck.standardFill();
		ArrayList<Deck> returnDeck = deck.deal2Hands(5);
		assertEquals(2, returnDeck.size());
		assertEquals(5, returnDeck.get(0).size());
		assertEquals(5, returnDeck.get(1).size());
	}
	
	@Test
	public void testDealNHands(){
		Deck newDeck = new Deck();
		newDeck.standardFill();
		ArrayList<Deck> returnDeck = deck.dealNHands(newDeck, 7, 5);
		assertEquals(6, returnDeck.size());
		assertEquals(7, returnDeck.get(0).size());
		assertEquals(7, returnDeck.get(1).size());
		assertEquals(7, returnDeck.get(2).size());
		assertEquals(7, returnDeck.get(3).size());
		assertEquals(7, returnDeck.get(4).size());
		assertEquals(17, returnDeck.get(5).size());

		deck = new Deck();
		deck.standardFill();
		returnDeck = deck.dealNHands(7, 5);
		assertEquals(5, returnDeck.size());
		assertEquals(7, returnDeck.get(0).size());
		assertEquals(7, returnDeck.get(1).size());
		assertEquals(7, returnDeck.get(2).size());
		assertEquals(7, returnDeck.get(3).size());
		assertEquals(7, returnDeck.get(4).size());
		assertEquals(17, deck.size());
		
	
	}
	
	/**
	 * 
	 */
	@Test
	public void testDivideDeck(){
		Deck newDeck = new Deck();
		newDeck.standardFill();
		ArrayList<Deck> returnDeck = deck.divideDeck(newDeck, 5);
		assertEquals(6, returnDeck.size());
		assertEquals(10, returnDeck.get(0).size());
		assertEquals(10, returnDeck.get(1).size());
		assertEquals(10, returnDeck.get(2).size());
		assertEquals(10, returnDeck.get(3).size());
		assertEquals(10, returnDeck.get(4).size());
		assertEquals(2, returnDeck.get(5).size());
		
		// Check if more decks than cards
		deck = new Deck();
		newDeck = new Deck();
		newDeck.standardFill();
		returnDeck = deck.divideDeck(newDeck, 53);
		assertEquals(54, returnDeck.size());
		assertEquals(52, returnDeck.get(53).size());

		
		//Method check for the stored deck
		deck = new Deck();
		deck.standardFill();
		returnDeck = deck.divideDeck(5);
		assertEquals(5, returnDeck.size());
		assertEquals(10, returnDeck.get(0).size());
		assertEquals(10, returnDeck.get(1).size());
		assertEquals(10, returnDeck.get(2).size());
		assertEquals(10, returnDeck.get(3).size());
		assertEquals(10, returnDeck.get(4).size());
		assertEquals(2, deck.size());
		
		//Checks for more decks than cards
		deck = new Deck();
		deck.standardFill();
		returnDeck = deck.divideDeck(53);
		assertEquals(53, returnDeck.size());
		assertEquals(0, returnDeck.get(0).size());
		assertEquals(0, returnDeck.get(51).size());
		assertEquals(0, returnDeck.get(52).size());
		assertEquals(52, deck.size());

	
	}


}
