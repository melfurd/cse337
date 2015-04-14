package project;

public class Card {

	private String rank;
	private int numRank;
	private String suit;
	public String compareToRank;
	private int numRealRank;
	private boolean faceUp;
	private boolean onTable;
	private String owner;
	
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
	
	public void setFaceUp(boolean value){
		this.faceUp=value;
	}
	
	public void setOwnership(String owner){
		this.owner = owner;
	}
	
	public String getOwner(){
		return owner;
	}
	
	public boolean isFaceUp(){
		return faceUp;
	}
	
	public void setOnTable(boolean value){
		this.onTable = value;
	}
	
	public boolean isOnTable(){
		return onTable;
	}
	
	public String getRank(){
		return rank;
	}
	
	public String getSuit(){
		return suit;
	}
	
	public int getNumRank(){
		return numRank;
	}
	
	public int getRealNumRank(){
		return numRealRank;
	}
	
	public String getCard(){
		if(faceUp){
			return rank+" of "+suit;
		}
		else{
			return "(Face Down Card)";
		}
	}
	
	public String getCompareToRank(){
		return compareToRank;
	}
}
