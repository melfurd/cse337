package project;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;


/**
 * The game of war uses two standard decks of cards, one for 
 * each player. Each player will draw one card at a time. The 
 * player with the higher card collects both cards. The player
 * who gains all of the cards wins. If there is a draw - each
 * players who gets a card of the same value, a war is declared.
 * For a was each player plays three cards one up and two face
 * down. Each card is flipped one at a time. The player that
 * wins at least 2 of the 3 matches wins all the cards
 * @author Stuart Nolton
 * @version 1.0
 * @since   2015-03-31 
 */
public class War implements CardGame{
	
	Deck d;
	private JButton draw;
	private JButton draw2;
	private JButton collect;
	private boolean player1Throws;
	private boolean player2Throws;
	private Deck player1Deck;
	private Deck player2Deck;
	private String message;
	
	/**
	 * Creates a new 2xStandard fill shuffled deck.
	 * The deck is then split in half, half for each player
	 * Creates buttons for each players and a collect button.
	 * The count of the cards in each deck is updated and 
	 * a empty message is displayed. 
	 */
	public War(){
		d = new Deck();
		d.standardFill();
		draw = new JButton("Player 1 Draw");
		draw2 = new JButton("Player 2 Draw");
		collect = new JButton("collect");
		ArrayList<Deck> splitDeck = d.divideDeck();
		player1Deck = splitDeck.remove(0);
		player2Deck = splitDeck.remove(0);
		player1Throws = false;
		player2Throws = false;
		updateCardCount();
		message="";
	}

	/* (non-Javadoc)
	 * 
	 * @see CardGame#ActionListener(java.awt.event.ActionEvent)
	 */
	@Override
	public void ActionListener(ActionEvent e) {
		//if button pressed is player one, it is player ones turn and there is a card to play
		//then display card on table and set ownership to player 1
		if(e.getSource()==draw&&!player1Throws){
			Card p1Card = player1Deck.drawCard(0);
			p1Card.setOwnership("player1");
			cardsOnTable.add(p1Card);
			sendMessage("Player 1 throws "+cardsOnTable.get(cardsOnTable.size()-1).getCard()+"\n");
			player1Throws=true;
		}
		//if button pressed is player two, it is player two turn and there is a card to play
		//then display card on table and set ownership to player 2
		if(e.getSource()==draw2&&!player2Throws){
			Card p2Card = player2Deck.drawCard(0);
			p2Card.setOwnership("player2");
			cardsOnTable.add(p2Card);
			sendMessage("Player 2 throws "+cardsOnTable.get(cardsOnTable.size()-1).getCard()+"\n");
			player2Throws=true;
		}
		//If both players have played a card and the collect button is pressed
		if(player2Throws&&player1Throws&&e.getSource()==collect){
			//if cards on the table match in rank declare war
			if(cardsOnTable.get(cardsOnTable.size()-1).getNumRank()==cardsOnTable.get(cardsOnTable.size()-2).getNumRank()){
				sendMessage("WAR!\neach player places a card face down click draw to throw your war card\n");
				Card warCard1 = player1Deck.drawCard(0);
				Card warCard2 = player2Deck.drawCard(0);
				warCard1.setFaceUp(false);
				warCard2.setFaceUp(false);
				cardsOnTable.add(warCard1);
				cardsOnTable.add(warCard2);
			}
			//if player 1 card is higher in rank then player 1 wins 
			//increase player one deck size and decrease player 2 deck size
			else if(cardsOnTable.get(cardsOnTable.size()-1).getNumRank()>cardsOnTable.get(cardsOnTable.size()-2).getNumRank()){
				if(cardsOnTable.get(cardsOnTable.size()-1).getOwner()=="player1"){
					sendMessage("Player 1 wins this hand\n");
					while(!cardsOnTable.isEmpty()){
						Card cardToAdd = cardsOnTable.remove(0);
						cardToAdd.setFaceUp(true);
						player1Deck.addCard(cardToAdd);
					}
				}
				else{
					sendMessage("Player 2 wins this hand\n");
					while(!cardsOnTable.isEmpty()){
						Card cardToAdd = cardsOnTable.remove(0);
						cardToAdd.setFaceUp(true);
						player2Deck.addCard(cardToAdd);
					}
				}
			}
			//if player 2 card is higher in rank then player 2 wins 
			//increase player 2 deck size and decrease player 1 deck size
			else{
				if(cardsOnTable.get(cardsOnTable.size()-1).getOwner()=="player1"){
					sendMessage("Player 2 wins this hand\n");
					while(!cardsOnTable.isEmpty()){
						Card cardToAdd = cardsOnTable.remove(0);
						cardToAdd.setFaceUp(true);
						player2Deck.addCard(cardToAdd);
					}
				}
				else{
					sendMessage("Player 1 wins this hand\n");
					while(!cardsOnTable.isEmpty()){
						Card cardToAdd = cardsOnTable.remove(0);
						cardToAdd.setFaceUp(true);
						player1Deck.addCard(cardToAdd);
					}
				}
			}
			player1Throws = false;
			player2Throws = false;
			updateCardCount();
		}
		checkForWin();
	}

	/* (non-Javadoc)
	 * @see CardGame#getCardsOnTable()
	 */
	@Override
	public ArrayList<Card> getCardsOnTable() {
		return cardsOnTable;
	}

	/* (non-Javadoc)
	 * add buttons to table top
	 * @see CardGame#getActions()
	 */
	@Override
	public ArrayList<JButton> getActions() {
		ArrayList<JButton> actions = new ArrayList<JButton>();
		actions.add(draw);
		actions.add(draw2);
		actions.add(collect);
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

	/* (non-Javadoc)
	 * Checks to see if there is a winner yet. For there
	 * to be a winner one player must not have any cards
	 * @see CardGame#checkForWin()
	 */
	@Override
	public void checkForWin() {
		if(player1Deck.size()==0){
			sendMessage("player 1 Wins");
		}
		else if(player2Deck.size()==0){
			sendMessage("player 2 Wins");
		}
		
	}

	/**
	 * Updates card count of each players deck
	 */
	public void updateCardCount(){
		sendMessage("\nplayer1 has "+player1Deck.size()+" cards left.\nplayer2 has "+player2Deck.size()+" cards left.\n\n");
	}
	
	/* (non-Javadoc)
	 * @see CardGame#usesHand()
	 */
	@Override
	public boolean usesHand() {
		return false;
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
		return null;
	}

}
