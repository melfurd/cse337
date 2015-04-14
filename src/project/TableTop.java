package project;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


/**
 * Table top provides the jPanel to serve as a table top
 * for the developer to place game objects like cards,
 * buttons, scores. 
 * @author Stuart Nolton
 * @version 1.0
 * @since   2015-03-31 
 */
public class TableTop extends JFrame implements ActionListener {
	
	private JTextArea table;
	private JTextArea msg;
	private CardGame game;
	private ArrayList<JButton> actions;

	
	/**
	 * Based on the game that is passed in as parameter
	 * the table top do the actions needed for that game
	 * and set up a space canvas for objects to be placed
	 * @param game
	 */
	public TableTop(CardGame game){
		this.game = game;
		this.actions = game.getActions();
		JPanel actionPanel = new JPanel();
		for(int x = 0;x<actions.size();x++){
			actions.get(x).addActionListener(this);
			actionPanel.add(actions.get(x));
		}
		JPanel tablePanel = new JPanel();
		JPanel messagePanel = new JPanel();
		table = new JTextArea(10,40);
		msg = new JTextArea(10,40);
		if(game.usesHand()){
			for(int x = 0;x<game.players();x++){
				JTextArea hand = new JTextArea(10,10);
				hand.setEditable(false);
				JFrame j = new JFrame("player "+(x+1)+" Hand");
				j.setSize(200, 200);
				j.setVisible(true);
				j.setLocation(600+20*x, 0);
				j.add(hand);
			}
		}
		table.setEditable(false);
		JScrollPane scroll = new JScrollPane (table);
		JScrollPane scroll2 = new JScrollPane (msg);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		tablePanel.add(scroll);
		messagePanel.add(scroll2);
		this.add(scroll, BorderLayout.NORTH);
		this.add(scroll2, BorderLayout.SOUTH);
		this.add(actionPanel);
		this.setSize(600, 600);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}
	
	/* (non-Javadoc)
	 * When game is chosen the table top is labeled and deck size
	 * is set number of cards in deck are set on the table
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		game.ActionListener(e);
		table.setText("");
		for(int x = 0;x<game.getCardsOnTable().size();x++){
			table.append(game.getCardsOnTable().get(x).getCard()+"\n");
		}
		msg.append(game.getMessage());
		game.clearMessage();
	}
	
	/**
	 * @param args
	 */
	public static void main(String args[]){
		//CardGame w = new War();
		CardGame w = new Poker();
		TableTop t = new TableTop(w);
	}
}
