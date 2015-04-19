package projectTest;
import java.util.*;

import project.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * DeckTest is the junit test to check methods in Card.java
 * @author Mel Chi and Stuart Nolton
 * @version 1.2 2015-04-08
 */
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
	
	/**
	 * Tests the draw card
	 */
	@Test
	public void testDrawCard(){
		deck.standardFill();
		deck.drawCard(0);
		assertEquals(51, deck.size());
		deck.drawCard(0);
		deck.drawCard(0);
		deck.drawCard(0);
		assertEquals(48, deck.size());
	}
	
	/**
	 * Tests the size of deck before and after a draw
	 */
	public void testGetSize(){
		deck.standardFill();
		assertEquals(52, deck.getSize());
		deck.drawCard(0);
		assertEquals(51, deck.getSize());
	}
	
	/**
	 * Tests adding cards gives the deck
	 */
	public void testAddCard(){
		deck.standardFill();
		deck.add(new Card(0,0));
		assertEquals(53, deck.getSize());
		deck.add(new Card(0,0));
		deck.add(new Card(0,0));
		assertEquals(55, deck.getSize());
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
	 * Tests dividing the decks by different methods
	 * Makes sure if not enough cards, the cards are added divided correctly
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
	
	/**
	 * Tests if the rank of the cards go from 2 up to aces
	 */
	@Test
	public void testSortDeckByNumRank(){
		deck.standardFill();
		deck = deck.sortDeckByNumRank(deck);
		assertEquals(2, deck.get(0).getRealNumRank());
		assertEquals(2, deck.get(1).getRealNumRank());
		assertEquals(2, deck.get(2).getRealNumRank());
		assertEquals(2, deck.get(3).getRealNumRank());
		assertEquals(3, deck.get(4).getRealNumRank());
		assertEquals(3, deck.get(5).getRealNumRank());
		assertEquals(3, deck.get(6).getRealNumRank());
		assertEquals(3, deck.get(7).getRealNumRank());
		assertEquals(14, deck.get(48).getRealNumRank());
		assertEquals(14, deck.get(49).getRealNumRank());
		assertEquals(14, deck.get(50).getRealNumRank());
		assertEquals(14, deck.get(51).getRealNumRank());
	}
	
	/**
	 * Tests if the deck is sorted by suit, rank does not matter
	 */
	@Test
	public void testSortDeckBySuit(){
		deck.standardFill();
		deck = deck.sortDeckBySuit(deck, "Hearts", "Diamonds", "Spades", "Clubs");
		assertEquals("Hearts", deck.get(0).getSuit());
		assertEquals("Diamonds", deck.get(15).getSuit());
		assertEquals("Spades", deck.get(30).getSuit());
		assertEquals("Clubs", deck.get(50).getSuit());

	}
	
	/**
	 * sorts by suit rank first, then sorts the ranks by suit
	 */
	@Test
	public void testSortDeckByRankingOfEachSuit(){
		Deck theDeck = new Deck();
		theDeck.standardFill();
		theDeck = deck.sortDeckByRankingOfEachSuit(theDeck, "Hearts", "Diamonds", "Spades", "Clubs");
		this.deck = theDeck;
		assertEquals(2, deck.get(0).getRealNumRank());
		assertEquals("Hearts", deck.get(0).getSuit());
		assertEquals(3, deck.get(1).getRealNumRank());
		assertEquals("Hearts", deck.get(1).getSuit());
		assertEquals(4, deck.get(2).getRealNumRank());
		assertEquals("Hearts", deck.get(2).getSuit());
		assertEquals(5, deck.get(3).getRealNumRank());
		assertEquals("Hearts", deck.get(3).getSuit());
		
		assertEquals(11, deck.get(48).getRealNumRank());
		assertEquals("Clubs", deck.get(48).getSuit());
		assertEquals(12, deck.get(49).getRealNumRank());
		assertEquals("Clubs", deck.get(49).getSuit());
		assertEquals(13, deck.get(50).getRealNumRank());
		assertEquals("Clubs", deck.get(50).getSuit());
		assertEquals(14, deck.get(51).getRealNumRank());
		assertEquals("Clubs", deck.get(51).getSuit());
	}
	




}
