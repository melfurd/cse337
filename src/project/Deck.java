package project;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class Deck extends ArrayList<Card> {
	
	private int nCards;
	private int nRanks;
	private int nSuits;
	
	public Deck(){
		
	}
	
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
	
	public Card drawCard(int index){
		Card drawnCard = this.remove(index);
		this.trimToSize();
		return drawnCard;
		
	}
	
	public int getSize(){
		return nCards;
	}
	
	public void addCard(Card e){
		this.add(e);
	}
	
	
	
	//public Card
	
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
