package project;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;


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
	}

	@Override
	public void ActionListener(ActionEvent e) {
		if(e.getSource()==draw&&!player1Throws){
			Card p1Card = player1Deck.drawCard(0);
			p1Card.setOwnership("player1");
			cardsOnTable.add(p1Card);
			sendMessage("Player 1 throws "+cardsOnTable.get(cardsOnTable.size()-1).getCard()+"\n");
			player1Throws=true;
		}
		if(e.getSource()==draw2&&!player2Throws){
			Card p2Card = player2Deck.drawCard(0);
			p2Card.setOwnership("player2");
			cardsOnTable.add(p2Card);
			sendMessage("Player 2 throws "+cardsOnTable.get(cardsOnTable.size()-1).getCard()+"\n");
			player2Throws=true;
		}
		if(player2Throws&&player1Throws&&e.getSource()==collect){
			if(cardsOnTable.get(cardsOnTable.size()-1).getNumRank()==cardsOnTable.get(cardsOnTable.size()-2).getNumRank()){
				sendMessage("WAR!\neach player places a card face down click draw to throw your war card\n");
				Card warCard1 = player1Deck.drawCard(0);
				Card warCard2 = player2Deck.drawCard(0);
				warCard1.setFaceUp(false);
				warCard2.setFaceUp(false);
				cardsOnTable.add(warCard1);
				cardsOnTable.add(warCard2);
			}
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
		}
	}

	@Override
	public ArrayList<Deck> getDecks() {
		ArrayList<Deck> theDeck = new ArrayList<Deck>();
		theDeck.add(d);
		return theDeck;
	}

	@Override
	public ArrayList<Card> getCardsOnTable() {
		return cardsOnTable;
	}

	@Override
	public ArrayList<JButton> getActions() {
		ArrayList<JButton> actions = new ArrayList<JButton>();
		actions.add(draw);
		actions.add(draw2);
		actions.add(collect);
		return actions;
	}

	@Override
	public void sendMessage(String message) {
		this.message = message;
	}
	
	public String getMessage(){
		return this.message;
	}

	@Override
	public int getPlayer() {
		return 2;
	}

}
