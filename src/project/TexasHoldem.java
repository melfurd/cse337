package project;
import java.awt.event.ActionEvent;
import java.util.*;

import javax.swing.JButton;

/**
 * 
 * @author Mel Chi and Stuart Nolton
 *
 */
public class TexasHoldem implements CardGame{
	
	private Deck tableDeck;
	private Deck tableShownDeck;
	public ArrayList<Card> cardsOnTable = new ArrayList<Card>();
	private ArrayList<JButton> checkAndBet = new ArrayList<JButton>();
	private ArrayList<JButton> foldHand = new ArrayList<JButton>();
	private ArrayList<Deck> hands = new ArrayList<Deck>();
	private PokerUtility poke = new PokerUtility();
	private JButton deal;
	private JButton winner;
	
	private String message;
	
	public boolean dealHands = false;
	public boolean preflop = false;
	public boolean flop = false;
	public boolean turn = false;
	public boolean river = false;
	public boolean whoWinner = false;
	
	private int numberOfPlayer = 0;
	public TexasHoldem(int numberOfPlayers){
		tableDeck = new Deck();
		numberOfPlayer = numberOfPlayers;
		
		tableDeck.standardFill();
		
		winner = new JButton("Winner");
		deal = new JButton("Deal");
		
		for(int i = 0; i < numberOfPlayers; i++){
			hands.add(new Deck());
			checkAndBet.add(new JButton("Player" + (i+1)));
			foldHand.add(new JButton("Fold Player" + (i+1)));
		}
		dealHands = true;
		
	}
	
	StringBuilder stringBuilder = new StringBuilder();
	@Override
	public void ActionListener(ActionEvent e) {
		if(e.getSource()== deal && dealHands){
			tableDeck = new Deck();
			tableShownDeck = new Deck();
			cardsOnTable = new ArrayList<Card>();
			
			
			tableDeck.standardFill();
			
			hands = new ArrayList<Deck>();
			for(int i = 0; i < numberOfPlayer; i++){
				hands.add(new Deck());
			}
			
			for(int i = 0; i < 2; i++){
				for (int j = 0; j < hands.size(); j++){
					hands.get(j).add(tableDeck.drawCard(0));
				}
			}
			dealHands = false;
			flop = true;
		}
		else if (e.getSource()== deal && flop){
			tableShownDeck.add(tableDeck.drawCard(0));
			tableShownDeck.add(tableDeck.drawCard(0));
			tableShownDeck.add(tableDeck.drawCard(0));
			
			this.cardsOnTable.add(tableShownDeck.get(0));
			this.cardsOnTable.add(tableShownDeck.get(1));
			this.cardsOnTable.add(tableShownDeck.get(2));
			
			
			flop = false;
			turn = true;
		}
		else if (e.getSource()== deal && turn){
			tableShownDeck.add(tableDeck.drawCard(0));
			this.cardsOnTable.add(tableShownDeck.get(3));
			
			turn = false;
			river = true;
		}
		else if (e.getSource()== deal && river){
			tableShownDeck.add(tableDeck.drawCard(0));
			this.cardsOnTable.add(tableShownDeck.get(4));
			
			river = false;
			whoWinner = true;
		}		
		else if (e.getSource() == winner && whoWinner || e.getSource() == deal && whoWinner){

			whoWins();
			whoWinner = false;
			dealHands = true;
			
		}
		

	}
	

	@Override
	public ArrayList<Card> getCardsOnTable() {
		return cardsOnTable;
	}

	
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
		return this.numberOfPlayer;
	}

	/* (non-Javadoc)
	 * @see CardGame#getHand(int)
	 */
	@Override
	public String getHand(int x) {
		String theHand ="";
		for (int j = 0; j < hands.get(x).size(); j++){
			theHand = theHand + hands.get(x).get(j).getCard()+"\n";

		}
		return theHand;
	}
	

	public void whoWins(){
		for(int i = 0; i < hands.size(); i++){
			hands.get(i).add(tableShownDeck.get(0));
			hands.get(i).add(tableShownDeck.get(1));
			hands.get(i).add(tableShownDeck.get(2));
			hands.get(i).add(tableShownDeck.get(3));
			hands.get(i).add(tableShownDeck.get(4));
		}
		
		
		poke = new PokerUtility();
		int playerSlotWin = 0;		
		Deck winningDeck = new Deck();
		winningDeck = hands.get(0);
		String message = "";
		for (int i = 1; i < hands.size(); i++){
			boolean whoWins = poke.compareDecks(winningDeck, hands.get(i));
			if (whoWins){
				message = poke.cardMessage;
			}
			else{
				winningDeck = hands.get(i);
				playerSlotWin = i;
				message = poke.cardMessage;
			}
		}
		
		
		sendMessage("Player" + (playerSlotWin+1) + ":" + message + "\n");
		
	}
	

	
}
	
	