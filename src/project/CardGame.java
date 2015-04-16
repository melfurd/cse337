package project;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;


/**
 * @author Stuart Nolton
 * @version 1.0
 * @since   2015-03-31 
 */
public interface CardGame{
	
	//an arrayList that contains all the cards currently on the table
	ArrayList<Card> cardsOnTable = new ArrayList<Card>();
	
	//gets the logic associated with the action buttons for a given game
	void ActionListener(ActionEvent e);
	
	//gets an arrayList that contains all the cards currently on the table (should return cardsOnTable)
	ArrayList<Card> getCardsOnTable();
	
	//gets an ArrayList that contains all the buttons associated with a given game
	ArrayList<JButton> getActions();
	
	//used to set a message for when TableTop calls getMessage()
	void sendMessage(String message);
	
	//gets the current message stored in the game
	String getMessage();
	
	//checks for the win conditions of the given game
	void checkForWin();
	
	//boolean for weather or not this game uses hands
	boolean usesHand();
	
	//if a game uses hands this method should return the hand for player number x
	String getHand(int x);
	
	//clears the message variable subsequent calls to getMessage should return ""
	void clearMessage();
	
	//returns the number of players for the given game
	int players();
}
