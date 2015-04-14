package project;

public class Card {

	private String rank;
	private int numRank;
	private String suit;
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
	
	public String getCard(){
		if(faceUp){
			return rank+" of "+suit;
		}
		else{
			return "(Face Down Card)";
		}
	}
}
