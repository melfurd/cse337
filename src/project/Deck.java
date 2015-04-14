package project;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


/**
 * @author Stuart Nolton
 * @version 1.0
 * @since   2015-03-31 
 */
public class Deck extends ArrayList<Card> {
	
	private int nCards;
	private int nRanks;
	private int nSuits;
	
	public Deck(){
		
	}
	
	/**
	 * Fills deck with a shuffled standard deck of cards
	 * including 52 cards with 13 in each suit
	 * ranking from 1 to 13.
	 */
	public void standardFill(){
		nCards=52;
		nRanks=13;
		nSuits=4;
		for(int x=0;x<nRanks;x++){
			for(int y=0;y<nSuits;y++){
				this.add(new Card(x,y));
			}
		}
		Collections.shuffle(this);
	}
	
	/**
	 * Deals a hand of n number of cards. Try block makes sure there
	 * are enough cards in the deck to complete hand. If
	 * there are not enough cards a message is printed.
	 * @param nCards
	 * @return one hand of n cards
	 */
	public Deck dealHand(int nCards){
		Deck hand = new Deck();
		try{
			for(int x=0;x<nCards;x++){
				hand.addCard(this.drawCard(0));
			}
		}
		catch(Exception e){
			System.out.println("delt more cards then where in the deck!");
		}
		return hand;
	}
	
	/**
	 * Deals two hands of n number of cards using two decks.
	 * Try block makes sure there are enough cards
	 * in both decks to deal hand. If there are not 
	 * enough cards a message is printed.
	 * @param nCards
	 * @return two hands of n cards
	 */
	public ArrayList<Deck> deal2Hands(int nCards){
		Deck d1 = new Deck();
		Deck d2 = new Deck();
		try{
			for(int x = 0; x<nCards;x++){
				d1.addCard(this.drawCard(0));
				d2.addCard(this.drawCard(0));
			}
		}
		catch(Exception e){
			System.out.println("not enough cards to deal 2 hands");
		}
		ArrayList<Deck> twoHands = new ArrayList<Deck>();
		twoHands.add(d1);
		twoHands.add(d2);
		return twoHands;
	}
	
	/**
	 * Splits deck into two equal parts and moves bottom half to top
	 * @return a cut deck
	 */
	public ArrayList<Deck> divideDeck(){
		Deck d1 = new Deck();
		Deck d2 = new Deck();
		while(this.size()!=0){
			if(this.size()%2==1){
				d1.addCard(this.drawCard(0));
			}
			else{
				d2.addCard(this.drawCard(0));
			}
		}
		ArrayList<Deck> splitDeck = new ArrayList<Deck>();
		splitDeck.add(d1);
		splitDeck.add(d2);
		return splitDeck;
	}
	
	/**
	 * Draws a card from this deck, remove this card from the deck.
	 * @param index
	 * @return drawn card
	 */
	public Card drawCard(int index){
		Card drawnCard = this.remove(index);
		this.trimToSize();
		return drawnCard;
		
	}
	
	/**
	 * get size of deck
	 * @return number of cards in deck
	 */
	public int getSize(){
		return nCards;
	}
	
	/**
	 * Adds a card to the deck
	 * @param e
	 */
	public void addCard(Card e){
		this.add(e);
	}
	
	
	//public Card
	
	/**
	 * Instantiates a deck of cards with a standard deck.
	 * Displays the deck of cards, removes a card and then 
	 * adds the card back to the top of the deck
	 * @param args
	 */
	public static void main(String args[]){
		Deck d = new Deck();
		d.standardFill();
		for(int x=0;x<52;x++){
			System.out.println(d.get(x).getCard());
		}
		System.out.println("this is the card you drew");
		Card myCard = d.drawCard(DeckUtil.TOP_OF_DECK);
		System.out.println(myCard.getCard());
		System.out.println("Deck after you drew");
		for(int x=0;x<51;x++){
			System.out.println(d.get(x).getCard());
		}
		System.out.println("going to add a card to the deck");
		d.add(myCard);
		System.out.println("deck aftee the addition");
		for(int x=0;x<52;x++){
			System.out.println(d.get(x).getCard());
		}
	}
}
