package projectTest;
import project.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import project.Deck;

/**
 * PokerUtilityTest is the junit test to check methods in Card.java
 * @author Mel Chi and Stuart Nolton
 * @version 1.2 2015-04-08
 */
public class PokerUtilityTest {

	Deck deck = new Deck();
	PokerUtility poke = new PokerUtility();

	@Before
	public void setUp() throws Exception {
		deck = new Deck();
		poke = new PokerUtility();
	}
	
	@Test
	public void testSortDeck(){
		deck.standardFill();
		deck = poke.sortDeck(deck);
	}
	
	
	@Test
	public void testCheckStraightFlush(){
		//Fail
		deck = new Deck();
		deck.add(new Card(11,2));
		deck.add(new Card(1,1));
		deck.add(new Card(10,0));
		deck.add(new Card(2,1));
		deck.add(new Card(8,0));
		assertFalse(poke.checkStraightFlush(deck));
		
		//Fail but has flush
		deck = new Deck();
		deck.add(new Card(11,1));
		deck.add(new Card(1,1));
		deck.add(new Card(10,1));
		deck.add(new Card(2,1));
		deck.add(new Card(8,1));
		assertFalse(poke.checkStraightFlush(deck));
		
		//Should work
		deck = new Deck();
		deck.add(new Card(12,1));
		deck.add(new Card(11,1));
		deck.add(new Card(10,1));
		deck.add(new Card(9,1));
		deck.add(new Card(8,1));
		assertTrue(poke.checkStraightFlush(deck));
		
		//Should work
		deck = new Deck();
		deck.add(new Card(12,1));
		deck.add(new Card(1,2));
		deck.add(new Card(10,1));
		deck.add(new Card(9,1));
		deck.add(new Card(8,1));
		deck.add(new Card(7,1));
		deck.add(new Card(6,1));
		assertTrue(poke.checkStraightFlush(deck));


	}
	
	@Test
	public void testCheckNumOfAKind(){
		//fail
		deck = new Deck();
		deck.add(new Card(11,2));
		deck.add(new Card(1,1));
		deck.add(new Card(10,0));
		deck.add(new Card(2,1));
		deck.add(new Card(8,0));
		assertEquals(0, poke.checkNumOfAKind(deck, 2, 0));

		//2 of a kind
		deck = new Deck();
		deck.add(new Card(11,2));
		deck.add(new Card(1,1));
		deck.add(new Card(10,0));
		deck.add(new Card(2,1));
		deck.add(new Card(1,0));
		assertEquals(3, poke.checkNumOfAKind(deck, 2, 0));
		
		//3 of a kind
		deck = new Deck();
		deck.add(new Card(11,2));
		deck.add(new Card(1,1));
		deck.add(new Card(1,2));
		deck.add(new Card(2,1));
		deck.add(new Card(1,0));
		assertEquals(3, poke.checkNumOfAKind(deck, 3, 0));
		
		//4 of a kind
		deck = new Deck();
		deck.add(new Card(11,2));
		deck.add(new Card(11,1));
		deck.add(new Card(11,0));
		deck.add(new Card(11,3));
		deck.add(new Card(1,0));
		assertEquals(13, poke.checkNumOfAKind(deck, 4, 0));

	}
	
		
	@Test
	public void testFullHouse(){
		//queens and eights
		deck.add(new Card(10,2));
		deck.add(new Card(10,1));
		deck.add(new Card(10,0));
		deck.add(new Card(8,1));
		deck.add(new Card(8,0));
		assertEquals(12, poke.checkFullHouse(deck));
		assertEquals(12, poke.highRank1);
		assertEquals(10, poke.highRank2);

		
		//fail none
		deck = new Deck();
		deck.add(new Card(11,2));
		deck.add(new Card(7,1));
		deck.add(new Card(10,0));
		deck.add(new Card(2,1));
		deck.add(new Card(8,0));
		assertEquals(0, poke.checkFullHouse(deck));
		
		//fail except with 3 of a kind
		deck = new Deck();
		deck.add(new Card(10,2));
		deck.add(new Card(10,1));
		deck.add(new Card(10,0));
		deck.add(new Card(9,1));
		deck.add(new Card(8,0));
		assertEquals(0, poke.checkFullHouse(deck));


	}
	
	
	@Test
	public void testCheckFlush(){
		deck.add(new Card(12,1));
		deck.add(new Card(3,1));
		deck.add(new Card(2,1));
		deck.add(new Card(1,1));
		deck.add(new Card(0,1));
		assertEquals("Diamonds", poke.checkFlush(deck));
		
		deck = new Deck();
		deck.add(new Card(12,1));
		deck.add(new Card(3,1));
		deck.add(new Card(2,2));
		deck.add(new Card(1,3));
		deck.add(new Card(0,1));
		deck.add(new Card(0,3));
		assertEquals("None", poke.checkFlush(deck));
	}
	
	@Test
	public void testCheckStraight(){
		
		//A-5
		deck.add(new Card(12,2));
		deck.add(new Card(10,1));
		deck.add(new Card(12,0));
		deck.add(new Card(3,1));
		deck.add(new Card(2,1));
		deck.add(new Card(1,0));
		deck.add(new Card(0,0));
		assertTrue(poke.checkStraight(deck));
		assertEquals(5, poke.highRank1);
		
		// Any
		deck = new Deck();
		deck.add(new Card(11,2));
		deck.add(new Card(10,1));
		deck.add(new Card(9,0));
		deck.add(new Card(9,1));
		deck.add(new Card(8,1));
		deck.add(new Card(7,0));
		deck.add(new Card(6,0));
		assertTrue(poke.checkStraight(deck));
		assertEquals(13, poke.highRank1);
		
		// Nope
		
		deck = new Deck();
		deck.add(new Card(11,2));
		deck.add(new Card(10,1));
		deck.add(new Card(6,0));
		deck.add(new Card(2,1));
		deck.add(new Card(1,1));
		deck.add(new Card(4,0));
		deck.add(new Card(6,0));
		assertFalse(poke.checkStraight(deck));
		
		deck = new Deck();
		deck.add(new Card(12,2));
		deck.add(new Card(11,1));
		deck.add(new Card(10,0));
		deck.add(new Card(2,1));
		deck.add(new Card(9,1));
		deck.add(new Card(4,0));
		deck.add(new Card(6,0));
		assertFalse(poke.checkStraight(deck));
		
	}
	
	@Test
	public void testCheckTwoPair() {
		
		//queens and eights
		deck.add(new Card(11,2));
		deck.add(new Card(10,1));
		deck.add(new Card(10,0));
		deck.add(new Card(8,1));
		deck.add(new Card(8,0));
		assertEquals(12, poke.checkTwoPair(deck));
		assertEquals(12, poke.highRank1);
		assertEquals(10, poke.highRank2);
		assertEquals(13, poke.highRank3);
		
		//fail
		deck = new Deck();
		deck.add(new Card(11,2));
		deck.add(new Card(7,1));
		deck.add(new Card(10,0));
		deck.add(new Card(2,1));
		deck.add(new Card(8,0));
		assertEquals(0, poke.checkTwoPair(deck));
		assertEquals(0, poke.highRank1);
		
	}
	
	@Test
	
	public void testCompareDeck(){
		deck = new Deck();
		deck.add(new Card(12,2));
		deck.add(new Card(11,1));
		deck.add(new Card(10,0));
		deck.add(new Card(9,1));
		deck.add(new Card(8,1));
		deck.add(new Card(7,0));
		deck.add(new Card(6,0));
		
		Deck deck2 = new Deck();
		deck2.add(new Card(10,2));
		deck2.add(new Card(10,1));
		deck2.add(new Card(9,0));
		deck2.add(new Card(2,1));
		deck2.add(new Card(3,1));
		deck2.add(new Card(7,0));
		deck2.add(new Card(6,0));
		assertTrue(poke.compareDecks(deck, deck2));
		System.out.println(poke.cardMessage);
		
		deck = new Deck();
		deck.add(new Card(12,2));
		deck.add(new Card(12,1));
		deck.add(new Card(12,0));
		deck.add(new Card(12,3));
		deck.add(new Card(8,1));
		deck.add(new Card(7,0));
		deck.add(new Card(6,0));
		
		deck2 = new Deck();
		deck2.add(new Card(12,1));
		deck2.add(new Card(11,1));
		deck2.add(new Card(10,1));
		deck2.add(new Card(9,1));
		deck2.add(new Card(8,1));
		deck2.add(new Card(7,0));
		deck2.add(new Card(6,0));

		assertFalse(poke.compareDecks(deck, deck2));
		System.out.println(poke.cardMessage);
	
		deck = new Deck();
		deck.add(new Card(11,2));
		deck.add(new Card(2,1));
		deck.add(new Card(0,0));
		deck.add(new Card(0,3));
		deck.add(new Card(8,1));
		deck.add(new Card(7,0));
		deck.add(new Card(6,0));
		
		deck2 = new Deck();
		deck2.add(new Card(12,1));
		deck2.add(new Card(11,2));
		deck2.add(new Card(10,3));
		deck2.add(new Card(9,1));
		deck2.add(new Card(8,1));
		deck2.add(new Card(7,0));
		deck2.add(new Card(6,0));

		assertFalse(poke.compareDecks(deck, deck2));
		System.out.println(poke.cardMessage);
	
	
		deck = new Deck();
		deck.add(new Card(11,2));
		deck.add(new Card(2,1));
		deck.add(new Card(0,0));
		deck.add(new Card(0,3));
		deck.add(new Card(8,1));
		deck.add(new Card(8,3));
		deck.add(new Card(6,0));
		
		deck2 = new Deck();
		deck2.add(new Card(9,1));
		deck2.add(new Card(7,2));
		deck2.add(new Card(10,3));
		deck2.add(new Card(8,0));
		deck2.add(new Card(8,1));
		deck2.add(new Card(0,2));
		deck2.add(new Card(0,1));

		assertTrue(poke.compareDecks(deck, deck2));
		System.out.println(poke.cardMessage);
	
	
		deck = new Deck();
		deck.add(new Card(11,2));
		deck.add(new Card(2,1));
		deck.add(new Card(5,0));
		deck.add(new Card(7,3));
		deck.add(new Card(8,1));
		deck.add(new Card(8,3));
		deck.add(new Card(6,0));
		
		deck2 = new Deck();
		deck2.add(new Card(9,1));
		deck2.add(new Card(7,2));
		deck2.add(new Card(10,3));
		deck2.add(new Card(8,0));
		deck2.add(new Card(8,1));
		deck2.add(new Card(1,2));
		deck2.add(new Card(0,1));

		assertTrue(poke.compareDecks(deck, deck2));
		System.out.println(poke.cardMessage);
	
	
	}

}
