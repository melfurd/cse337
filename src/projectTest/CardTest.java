package projectTest;
import project.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * CardTest is the junit test to check methods in Card.java
 * @author Mel Chi and Stuart Nolton
 * @version 1.2 2015-04-08
 */
public class CardTest {

	Card card;
	
	@Before
	public void setUp() throws Exception {
		card = new Card(0,0);
	}
	

	/**
	 * Test for setFaceUp and isFaceUp method.
	 * Will set the value of face and retrieve it
	 */
	@Test
	public void testIsFaceUp(){
		card.setFaceUp(true);
		assertTrue(card.isFaceUp());
	}
	
	/**
	 * Tests the setOnTable and getOwner method
	 */
	@Test
	public void testGetOwner(){
		card.setOwnership("Mel");
		assertEquals("Mel", card.getOwner());
	}
	
	/**
	 * Sets true or false on the table with setOnTable
	 * Gets the true or false in the isOnTable method.
	 */
	@Test
	public void testOnTable(){
		card.setOnTable(true);
		assertTrue(card.isOnTable());
	}
	
	
	/**
	 * Tests the ranks and suits methods
	 */
	@Test
	public void test() {

		assertEquals("Hearts", card.getSuit());
		assertEquals("2", card.getRank());
		assertEquals(0, card.getNumRank());
		assertEquals(2, card.getRealNumRank());
		assertEquals(2, card.getNumericalRank());
	}
	
	/**
	 * Tests the conversions if someone needs, maybe 
	 * this should become a static class by itself
	 */
	@Test
	public void testGetNumRank(){
		assertEquals("2", card.convertNumRankToRank(0));
		assertEquals("2", card.convertRealNumRankToRank(2));
		assertEquals(0, card.convertRankToNumRank("2"));
		assertEquals(2, card.convertRankToRealNumRank("2"));
	}
	


}
