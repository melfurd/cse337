package project;
import java.awt.event.ActionEvent;
import java.util.*;

import javax.swing.JButton;

/**
 * 
 * @author Mel Chi and Stuart Nolton
 *
 */
public class Poker2 implements CardGame{
	
	private Deck d;
	private JButton deal;
	private JButton winner;
	private Deck player1Cards;
	private Deck player2Cards;
	private int cardNumber;
	private boolean dealCards = true;
	private boolean winCards = false;
	private String message;
	

	public Poker2(){
		d = new Deck();
		d.standardFill();
		winner = new JButton("Winner");
		deal = new JButton("Deal");
		this.player1Cards = new Deck();
		this.player2Cards = new Deck();
		
	}

	StringBuilder stringBuilder = new StringBuilder();
	@Override
	public void ActionListener(ActionEvent e) {
		if(e.getSource()== deal && dealCards){
			this.player1Cards = new Deck();
			this.player2Cards = new Deck();
			clearMessage();
			stringBuilder = new StringBuilder();
			d = new Deck();
			d.standardFill();
			cardNumber = 0;
			//1
			player1Cards.addCard(d.drawCard(0));
			player2Cards.addCard(d.drawCard(0));
			
			//2
			player1Cards.addCard(d.drawCard(0));
			player2Cards.addCard(d.drawCard(0));
			//3
			player1Cards.addCard(d.drawCard(0));
			player2Cards.addCard(d.drawCard(0));
			
			//4
			player1Cards.addCard(d.drawCard(0));
			player2Cards.addCard(d.drawCard(0));
			
			//5
			player1Cards.addCard(d.drawCard(0));
			player2Cards.addCard(d.drawCard(0));
			
			dealCards = false;
			winCards = true;
		}
		else if (e.getSource() == winner && winCards){

			whoWins();
			dealCards = true;
			winCards = false;
			
		}
		

	}
	

	@Override
	public ArrayList<Card> getCardsOnTable() {
		return theDeck;
	}
	
	Deck theDeck = new Deck();
	
	
	@Override
	public ArrayList<JButton> getActions() {
		ArrayList<JButton> actions = new ArrayList<JButton>();
		actions.add(deal);
		actions.add(winner);
		return actions;
	}
	
	/* (non-Javadoc)
	 * @see CardGame#sendMessage(java.lang.String)
	 */
	
	@Override
	public void sendMessage(String message) {
		this.message+=message;
	}
	
	/* (non-Javadoc)
	 * @see CardGame#clearMessage()
	 */
	public void clearMessage(){
		this.message="";
	}
	
	/* (non-Javadoc)
	 * @see CardGame#getMessage()
	 */
	public String getMessage(){
		return this.message;
	}

	
	@Override
	public void checkForWin() {
		
	}
	
	/**
	 * Updates card count of each players deck
	 */
	public void updateCardCount(){
	}
	
	/* (non-Javadoc)
	 * @see CardGame#usesHand()
	 */
	@Override
	public boolean usesHand() {
		return true;
	}

	/* (non-Javadoc)
	 * returns/sets the number or players for the game
	 * for which the rules of the war determine to be two
	 * @see CardGame#players()
	 */
	@Override
	public int players() {
		return 2;
	}

	/* (non-Javadoc)
	 * @see CardGame#getHand(int)
	 */
	@Override
	public String getHand(int x) {
		String theHand ="";
		if(x==0){
			for(int y=0;y<player1Cards.size();y++){
				theHand = theHand+player1Cards.get(y).getCard()+"\n";
			}
		}
		else if(x==1){
			for(int y=0;y<player2Cards.size();y++){
				theHand = theHand+player2Cards.get(y).getCard()+"\n";
			}
		}
		else{
			return null;
		}
		return theHand;
	}
	

	public void whoWins(){
		PokerUtility pu = new PokerUtility();
		boolean compare = pu.compareDecks(player1Cards, player2Cards);
		String message = "";
		if (compare && pu.tieDeck){
			message = "PLAYER 1 AND 2:" + pu.cardMessage;
		}
		else if (compare){
			message = "PLAYER 1:" + pu.cardMessage;
		}
		else{
			message = "PLAYER 2:" + pu.cardMessage;
		}

		
	}
	

	
}
	
	