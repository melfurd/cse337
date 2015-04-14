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


public class TableTop extends JFrame implements ActionListener {
	
	private JTextArea table;
	private JTextArea msg;
	private CardGame game;
	private ArrayList<JButton> actions;

	
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
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		game.ActionListener(e);
		table.setText("");
		for(int x = 0;x<game.getCardsOnTable().size();x++){
			table.append(game.getCardsOnTable().get(x).getCard()+"\n");
		}
		msg.append(game.getMessage());
		game.sendMessage("");
	}
	
	public static void main(String args[]){
		//CardGame w = new War();
		CardGame w = new Poker();
		TableTop t = new TableTop(w);
	}
}
