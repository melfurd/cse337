package project;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;


public interface CardGame{
	
	ArrayList<Deck> Decks = new ArrayList<Deck>();
	
	ArrayList<Card> cardsOnTable = new ArrayList<Card>();
	
	void ActionListener(ActionEvent e);
	
	ArrayList<Deck> getDecks();
	
	ArrayList<Card> getCardsOnTable();
	
	ArrayList<JButton> getActions();
	
	int getPlayer();
	
	void sendMessage(String message);
	
	String getMessage();
	
	
}
