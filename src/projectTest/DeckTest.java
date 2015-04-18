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
	
	@Test
	public void testDealHands(){
		deck = new Deck();
		deck.standardFill();
		Deck returnDeck = deck.dealHand(5);
		assertEquals(5, returnDeck.size());
	}
	
	@Test
	public void testdeal2Hands(){
		deck = new Deck();
		deck.standardFill();
		ArrayList<Deck> returnDeck = deck.deal2Hands(5);
		assertEquals(2, returnDeck.size());
		assertEquals(5, returnDeck.get(0).size());
		assertEquals(5, returnDeck.get(1).size());
	}

}
