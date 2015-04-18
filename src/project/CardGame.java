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

	/**
	 * An ArrayList of type Card meant to hold any and all cards on the playing table. the cards in this ArrayList are displayed in the top panel of the TableTop JFrame.
	 * @deprecated The return type for this method will most likely be changed to type Deck
	 */
	ArrayList<Card> cardsOnTable = new ArrayList<Card>();
	
	/** This method plays the role of a traditional ActionListener. It should contain the logic for game actions. (works together with getActions())
	 * http://docs.oracle.com/javase/7/docs/api/java/awt/event/ActionListener.html
	 * @param e ActionEvent
	 */
	void ActionListener(ActionEvent e);

	/**
	 * This method is meant to return the current cards on the table
	 * Returns An ArrayList of type card representing the cards currently on the table.
	 * Warning This method should return the variable cardsOnTable specifically.
	 * @return An arraylist of cards
	 * @deprecated The return type for this method will most likely be changed to type Deck.
	 */
	
	ArrayList<Card> getCardsOnTable();
	
	/**
	 * This method is meant to grab any and all global action buttons associated with a given game.
	 * @return An ArrayList of type JButton representing the table actions that can be performed by any player. (works together with ActionListener)
	 * Warning In a future update there will be another method similar to this one. The future method will grab player specific action buttons as opposed to global action buttons.
	 */
	ArrayList<JButton> getActions();
	
	/**
	 * @param message- Text meant to be displayed in the bottom panel of the TableTop JFrame.
	 * Warning The way TableTop is set up, it is advised you write this method such that message concatenates with previous messages until clearMessage is called.
	 * @deprecated This method is not used by the TableTop class. although most card games will require it, it is not completely necessary and will most likely disappear.
	 */
	void sendMessage(String message);
	
	/**
	 * This method is used by TableTop to grab the current message that game should be displaying. This method gets called once per action. Right after this method is called, clearMessage() is called to reset the message variable.
	 * @return the message that is currently saved.
	 */
	String getMessage();
	
	/**
	 * This method is used to check to see if a player has won the game.
	 * @deprecated This method is not used by the TableTop class. Although most card games require this method, it is not directly required to interface with TableTop.
	 */
	void checkForWin();
	
	/**
	 * This method is called by TableTop to check if the game uses hands.
	 * @return True if this card game uses hands. false if this game does not use hands.
	 */
	boolean usesHand();
	
	/**
	 * This method gets a String representation of a player's hand.
	 * @param player- the player whos hand you wish to access.
	 * @return a string representing the players hand
	 * Warning: This method does not return a Deck or ArrayList representaion of the hand. It is used for display puposes. Another method will be added in a future update to suport access the hand as a Deck.
	 */
	String getHand(int player);

	/**
	 * clears the message variable subsequent calls to getMessage should return "".
	 * Warning This method is automaticly called in TableTop at the end of any action handled by ActionPerformed.
	 */
	void clearMessage();
	
	/**
	 * This should return the number of players for a given game
	 * @return The number of players for this game.
	 */
	int players();
}
