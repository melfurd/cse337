package project;
/**
 * Creates all the standard cards to be used in standard decks
 * including 4 suites and 13 ranks for a total of 52 standard cards.
 * @author Stuart Nolton
 * @version 1.0
 * @since   2015-03-31 
 */
public class Card {

	private String rank; //string representation of the Rank
	private int numRank; //integer representation of the Rank
	private String suit; //Sting representation of the suit
	private boolean faceUp; //boolean for face up face down true = face up, false = face down
	private boolean onTable; //boolean for on the table and off the table true = on the table, false = off the table
	private String owner; //String value to assign ownership to a card.
	public String compareToRank;
	private int numRealRank;
	
	
	
	/**
	 * This creates a new card with the rank and suit given as integers.
	 * @param rank
	 * @param suit
	 */
	public Card(int rank, int suit){
		this.faceUp=true;
		this.numRank = rank;
		switch(suit){
		case 0:
			this.suit = "Hearts";
			break;
		case 1:
			this.suit = "Diamonds"; 
			break;
		case 2:
			this.suit = "Spades";
			break;
		case 3:
			this.suit = "Clubs";
		}
		switch(rank){
		case 0:
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
			this.rank = Integer.toString(rank+2);
			break;
		case 9:
			this.rank = "Jack";
			break;
		case 10:
			this.rank = "Queen";
			break;
		case 11:
			this.rank = "King";
			break;
		case 12:
			this.rank = "Ace";
			break;
		}
		
		switch(rank){
		case 0:
			this.numRealRank = 2;
			this.compareToRank = "A";
			break;
		case 1:
			this.numRealRank = 3;
			this.compareToRank = "B";
			break;
		case 2:
			this.numRealRank = 4;
			this.compareToRank = "C";
			break;
		case 3:
			this.numRealRank = 5;
			this.compareToRank = "D";
			break;
		case 4:
			this.numRealRank = 6;
			this.compareToRank = "E";
			break;
		case 5:
			this.numRealRank = 7;
			this.compareToRank = "F";
			break;
		case 6:
			this.numRealRank = 8;
			this.compareToRank = "G";
			break;
		case 7:
			this.numRealRank = 9;
			this.compareToRank = "H";
			break;
		case 8:
			this.numRealRank = 10;
			this.compareToRank = "I";
			break;
		case 9:
			this.numRealRank = 11;
			this.compareToRank = "J";
			break;
		case 10:
			this.numRealRank = 12;
			this.compareToRank = "K";
			break;
		case 11:
			this.numRealRank = 13;
			this.compareToRank = "L";
			break;
		case 12:
			this.numRealRank = 14;
			this.compareToRank = "M";
			break;
		}
	}
	

	/**
	 * This method is used to set the card face up or face down.
	 * @param value boolean used to set card face up or face down true = face up.
	 */
	public void setFaceUp(boolean value){
		this.faceUp=value;
	}
	
	/**
	 * This method is used to set an owner for the card
	 * @param owner this is the name of the owener of the card
	 */
	public void setOwnership(String owner){
		this.owner = owner;
	}
	
	/** 
	 * This method gets the owner of the card.
	 * @return a String representing the owner of the card.
	 */
	public String getOwner(){
		return owner;
	}
	
	/**
	 * This method returns true if the card is face up.
	 * @return true if the card is face up false if the card is face down
	 */
	public boolean isFaceUp(){
		return faceUp;
	}
	
	/**
	 * setter method for setting this card on the "table"
	 * @param value set true if the card is on the table, set false if the card is off the table
	 */
	public void setOnTable(boolean value){
		this.onTable = value;
	}
	
	/**
	 * This method returns true if the card is on the "table"
	 * @return true if the card is on the table false if the card is off the table.
	 */
	public boolean isOnTable(){
		return onTable;
	}
	
	/**
	 * returns a String represntation of this card's rank
	 * @return a String representation of the Rank
	 */
	public String getRank(){
		return rank;
	}
	
	/**
	 * returns a String representation of this card's suit
	 * @return a String representation of the suit
	 */
	public String getSuit(){
		return suit;
	}
	
	/**
	 * returns a numerical representation of this card's rank
	 * @return an integer representation of this card's rank
	 */
	public int getNumRank(){
		return numRank;
	}
	
	/**
	 * returns this card as a String in the form rank+" of "+suit. if the card is face down then an empty string is returned.
	 * @return 
	 */
	public String getCard(){
		if(faceUp){
			return rank+" of "+suit;
		}
		else{
			return "(Face Down Card)";
		}
	}
	public int getRealNumRank(){
		return numRealRank;
	}
	
	
	public String getCompareToRank(){
		return compareToRank;
	}
}
