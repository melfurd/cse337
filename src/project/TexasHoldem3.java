package project;
import java.awt.event.ActionEvent;
import java.util.*;

import javax.swing.JButton;

/**
 * 
 * @author Mel Chi
 *
 */
public class TexasHoldem3 implements CardGame{
	
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
	
	private int dealer;
	
	private int numberOfPlayer = 0;
	public TexasHoldem3(int numberOfPlayers){
		tableDeck = new Deck();
		if (numberOfPlayers > 8){
			numberOfPlayers = 8;
		}
		if (numberOfPlayers < 2){
			numberOfPlayers = 2;
		}
		this.dealer = 0;
		
		numberOfPlayer = numberOfPlayers;
		addButtons(numberOfPlayers);
		
		tableDeck.standardFill();
		
		winner = new JButton("Winner");
		deal = new JButton("Deal");
		
		for(int i = 0; i < numberOfPlayers; i++){
			hands.add(new Deck());
		}
		dealHands = true;
		
	}
	
	private void changeDealer(){
		if (this.dealer >= this.numberOfPlayer){
			this.dealer = 1;
		}
		else{
			this.dealer++;
		}
	}
	
	private void addButtons(int numberOfPlayers){
		if (numberOfPlayers >= 2){
			checkAndBet.add(new JButton("Player 1 Check"));
			foldHand.add(new JButton("Player 1 Fold"));
			checkAndBet.add(new JButton("Player 2 Check"));
			foldHand.add(new JButton("Player 2 Fold"));
		}
		
		if (numberOfPlayers >= 3){
			checkAndBet.add(new JButton("Player 3 Check"));
			foldHand.add(new JButton("Player 3 Fold"));
		}
		
		if (numberOfPlayers >= 4){
			checkAndBet.add(new JButton("Player 4 Check"));
			foldHand.add(new JButton("Player 4 Fold"));
		}
		
		if (numberOfPlayers >= 5){
			checkAndBet.add(new JButton("Player 5 Check"));
			foldHand.add(new JButton("Player 5 Fold"));
		}
		
		if (numberOfPlayers >= 6){
			checkAndBet.add(new JButton("Player 6 Check"));
			foldHand.add(new JButton("Player 6 Fold"));
		}
		
		if (numberOfPlayers >= 7){
			checkAndBet.add(new JButton("Player 7 Check"));
			foldHand.add(new JButton("Player 7 Fold"));
		}
		
		if (numberOfPlayers >= 8){
			checkAndBet.add(new JButton("Player 8 Check"));
			foldHand.add(new JButton("Player 8 Fold"));
		}
		
		
	}
	
	
	StringBuilder stringBuilder = new StringBuilder();
	@Override
	public void ActionListener(ActionEvent e) {
		if(e.getSource()== deal && dealHands){
			tableDeck = new Deck();
			tableShownDeck = new Deck();
			cardsOnTable = new ArrayList<Card>();
			changeDealer();
			
			
			tableDeck.standardFill();
			
			hands = new ArrayList<Deck>();
			for(int i = 0; i < numberOfPlayer; i++){
				hands.add(new Deck());
			}
			
			dealCycle();
			
//			for(int i = 0; i < 2; i++){
//				for (int j = 0; j < hands.size(); j++){
//					hands.get(j).add(tableDeck.drawCard(0));
//				}
//			}
			dealHands = false;
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
		
		else if (e.getSource()== checkAndBet.get(0)){
			
		}
		
		else if (e.getSource()== foldHand.get(0)){
			
		}
		

	}
	
	private void dealCycle(){
		for(int i = 0; i < 2; i++){
			for (int j = 0; j < hands.size(); j++){
				if (j+dealer <= hands.size()){
					hands.get(j+dealer-1).add(tableDeck.drawCard(0));
				}
				else{
					hands.get(j+dealer-hands.size()-1).add(tableDeck.drawCard(0));
				}
			}
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
		
		for (int i = 0; i < checkAndBet.size(); i++){
			actions.add(checkAndBet.get(i));
			actions.add(foldHand.get(i));
		}
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
	
	