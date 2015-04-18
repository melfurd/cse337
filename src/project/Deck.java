package project;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;


/**
 * @author Stuart Nolton and Mel Chi
 * @version 2.0 2015-04-13
 * @since   version 1.0 2015-03-31 
 */
public class Deck extends ArrayList<Card> {
	
	private int nCards;
	private int nRanks;
	private int nSuits;
	
	/**
	 * Public constructor allocates space for a deck, you can fill with any cards
	 */
	public Deck(){
		
	}
	
	/**
	 * Fills deck with a shuffled standard deck of cards
	 * including 52 cards with 13 in each suit
	 * ranking from 0 to 12.
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
	 * @param nCards Number of cards you want to add
	 * @return one hand of n cards
	 * Warning: will not overdeal cards, will end when the deck is drawn
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
	 * This method is to be deprecated. Please use dealNHands method.
	 * Deals two hands of n number of cards using two decks.
	 * Try block makes sure there are enough cards
	 * in both decks to deal hand. If there are not 
	 * enough cards a message is printed.
	 * @param nCards Number of cards
	 * @return two hands of n cards
	 * @deprecated 
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
	 * Deals nHands given the deck to deal, the number of cards per deck and hands
	 * If there are not enough cards it will deal it evenly and the last extra deck
	 * contains the extra cards
	 * @param theDeck The input deck to deal to hands
	 * @param nCards The number of cards to be dealt per hand
	 * @param nHands The number of hands (deck)
	 * @return Returns the splitteddeck
	 */
	public ArrayList<Deck> dealNHands(Deck theDeck, int nCards, int nHands){
		ArrayList<Deck> splitDeck = new ArrayList<Deck>();
		if (theDeck.size() >= nCards*nHands){
			for (int d = 0; d < nHands; d++){
				splitDeck.add(new Deck());
			}
			
			for (int i = 0; i < nCards/nHands; i++){
				for (int j = 0; j < splitDeck.size(); j++){
					if (theDeck.size() <= 0){
						return splitDeck;
					}
					else{
						splitDeck.get(j).addCard(theDeck.drawCard(0));
					}

				}
			}
			
			int leftOverDeckSize = theDeck.size();
			Deck leftOverDeck = new Deck();
			while (leftOverDeckSize > 0){
				leftOverDeck.add(theDeck.drawCard(0));
				leftOverDeckSize--;
			}
			
		}
		else{
			splitDeck = divideDeck(theDeck, nHands);
		}
		
		return splitDeck;
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
	 * This will split the deck one card at a time to different decks.
	 * If extra cards remain, it will be put into the final deck on the
	 * arrayList returned.
	 * @param deckToSplit The deck to split
	 * @param numDecks Number of decks
	 * @return The splitted deck, the last deck will have the overflow
	 */
	public ArrayList<Deck> divideDeck(Deck deckToSplit, int numDecks){
		ArrayList<Deck> splitDeck = new ArrayList<Deck>();
		for (int d = 0; d < numDecks; d++){
			splitDeck.add(new Deck());
		}
		int numCardinDeck = deckToSplit.getSize();
		
		
		for (int i = 0; i < numCardinDeck/numDecks; i++){
			for (int j = 0; j < splitDeck.size(); j++){
				splitDeck.get(j).addCard(deckToSplit.drawCard(0));
			}
		}
		
		splitDeck.add(deckToSplit);
		return splitDeck;
	}
	
	/**
	 * The deck to divide
	 * @param numDecks The number of decks you want to divde the deck to
	 * @return Returns the splitted deck
	 */
	public ArrayList<Deck> divideDeck(int numDecks){
		ArrayList<Deck> splitDeck = new ArrayList<Deck>();
		for (int d = 0; d < numDecks; d++){
			splitDeck.add(new Deck());
		}
		int numCardinDeck = this.getSize();
		
		
		for (int i = 0; i < numCardinDeck/numDecks; i++){
			for (int j = 0; j < splitDeck.size(); j++){
				splitDeck.get(j).addCard(this.drawCard(0));
			}
		}
		
		splitDeck.add(this);
		return splitDeck;
	}
	

	
	/**
	 * Draws a card from this deck, remove this card from the deck.
	 * @param index The index of the card in the deck you want to remove
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
	 * @param e The card object that you want to add to the deck
	 */
	public void addCard(Card e){
		this.add(e);
	}
	
	/**
	 * Sorts the Deck, according to the rank, does not matter what the suit is.
	 * @param theDeck Input Deck
	 * @return Returns sorted deck that sorts by numRank
	 */
	public Deck sortDeckByNumRank(Deck theDeck){
		Collections.sort(theDeck, new Comparator<Card>(){
			public int compare(Card s1, Card s2){
				return s1.getCompareToRank().compareTo(s2.getCompareToRank());
			}
		});
		return theDeck;
	}

/**
 * Method sorts the deck by suit depending on the order you would like it by
 * the different param inputs.
 * @param theDeck The deck you want to sort by suit
 * @param cardType1 Input: "Hearts", "Diamonds", "Spades", "Clubs"
 * @param cardType2 Input: "Hearts", "Diamonds", "Spades", "Clubs"
 * @param cardType3 Input: "Hearts", "Diamonds", "Spades", "Clubs"
 * @param cardType4 Input: "Hearts", "Diamonds", "Spades", "Clubs"
 * @return Returns the deck in the order of the card Type Inputs
 * Warning: if the input types are duplicate, it will add duplicates of the card
 * I.E. if theDeck has 2 hearts, and you have cardType1 and cardType2 as "Hearts"
 * the deck will have the 2 hearts twice
 * Warning: if you do not input the correct type of card, it will not add whatever
 * type you are missing or the incorrect type of card
 */
	public Deck sortDeckBySuit(Deck theDeck, String cardType1,
			String cardType2, String cardType3, String cardType4){
		Deck theNewDeck = new Deck();
		for (int i = 0; i < theDeck.size(); i++){
			if (theDeck.get(i).getSuit().equals(cardType1)){
				theNewDeck.add(theDeck.get(i));
			}
		}
		for (int i = 0; i < theDeck.size(); i++){
			if (theDeck.get(i).getSuit().equals(cardType2)){
				theNewDeck.add(theDeck.get(i));
			}
		}
		for (int i = 0; i < theDeck.size(); i++){
			if (theDeck.get(i).getSuit().equals(cardType3)){
				theNewDeck.add(theDeck.get(i));
			}
		}
		for (int i = 0; i < theDeck.size(); i++){
			if (theDeck.get(i).getSuit().equals(cardType4)){
				theNewDeck.add(theDeck.get(i));
			}
		}
		
		
		return theNewDeck;
	}
	
	/**
	 * Method sorts the deck by suit and will be placed in order depending on the order you would like it by
	 * the different param inputs.
	 * @param theDeck The deck that you want to sort
	 * @param cardType1 Input: "Hearts", "Diamonds", "Spades", "Clubs"
	 * @param cardType2 Input: "Hearts", "Diamonds", "Spades", "Clubs"
	 * @param cardType3 Input: "Hearts", "Diamonds", "Spades", "Clubs"
	 * @param cardType4 Input: "Hearts", "Diamonds", "Spades", "Clubs"
	 * @return Returns the deck in the order of the card Type Inputs
	 * Warning: if the input types are duplicate, it will add duplicates of the card
	 * I.E. if theDeck has 2 hearts, and you have cardType1 and cardType2 as "Hearts"
	 * the deck will have the 2 hearts twice and in order
	 *	Warning: if you do not input the correct type of card, it will not add whatever
	 * type you are missing or the incorrect type of card
	 */
		public Deck sortDeckBySuitAndRank(Deck theDeck, String cardType1,
				String cardType2, String cardType3, String cardType4){
				Deck theNewDeck = sortDeckByNumRank(theDeck);
				return sortDeckBySuit(theNewDeck, cardType1, cardType2, cardType3, cardType4);
		}

}
