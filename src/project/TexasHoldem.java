//package project;
//import java.awt.List;
//import java.awt.event.ActionEvent;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.*;
//import javax.swing.JButton;
//
//
//public class TexasHoldem implements CardGame{
//	
//	private Deck d;
//	private Deck table;
//	private JButton check;
//	private JButton check2;
//	private JButton deal;
//	private Deck player1Cards;
//	private Deck player2Cards;
//	
//	private String message;
//
//	private Card flop1Card;
//	private Card flop2Card;
//	private Card flop3Card;
//	private Card turnCard;
//	private Card riverCard;
//	
//	private boolean player1Check;
//	private boolean player2Check;
//	
//	private boolean preflop;
//	private boolean flop;
//	private boolean turn;
//	private boolean river;
//	private boolean gameon;
//	private boolean gamestarted;
//	
//	
//	private int cardnumber = 0;
//	
//	public TexasHoldem(){
//		d = new Deck();
//		d.standardFill();
//		check = new JButton("Check P1");
//		check2 = new JButton("Check P2");
//		deal = new JButton("Deal");
//		
//	}
//
//	@Override
//	public void ActionListener(ActionEvent e) {
//		if(e.getSource()== deal){
//			if (!gamestarted){
//				d = new Deck();
//				d.standardFill();
//				cardnumber = 0;
//				this.player1Cards = new Deck();
//				this.player2Cards = new Deck();
//				player1Cards.addCard(d.drawCard(cardnumber));
//				cardnumber++;
//				player2Cards.addCard(d.drawCard(cardnumber));
//				cardnumber++;
//				player1Cards.addCard(d.drawCard(cardnumber));
//				cardnumber++;
//				player2Cards.addCard(d.drawCard(cardnumber));
//				cardnumber++;
//				sendMessage("Players have been dealt card.");
//				preflop = true;
//				gamestarted = true;
//			}
//
//		}
//		else if (e.getSource() == check){
//			player1Check = true;
//			sendMessage("Player 1 check");
//			if (preflop){
//				preflop = false;
//				flop = true;
//			}
//			if (flop){
//				flop = false;
//				turn = true;
//			}
//			if (turn){
//				turn = false;
//				river = true;
//			}
//			if (river){
//				river = false;
//				gameon = true;
//			}
//			
//		}
//		else if (e.getSource() == check2){
//			player2Check = true;
//			sendMessage("Player 2 check");
//			if (preflop){
//				preflop = false;
//				flop = true;
//			}
//			if (flop){
//				flop = false;
//				turn = true;
//			}
//			if (turn){
//				turn = false;
//				river = true;
//			}
//			if (river){
//				river = false;
//				gameon = true;
//			}
//		}
//		
//		if (player1Check && player2Check){
//			if (flop){
//				sendMessage("The flop is");
//				flop1Card = d.drawCard(cardnumber);
//				cardnumber++;
//				table.addCard(flop1Card);
//				sendMessage(flop1Card.getRank() + " " + flop1Card.getSuit());
//				
//				flop2Card = d.drawCard(cardnumber);
//				cardnumber++;
//				table.addCard(flop2Card);
//				sendMessage(flop2Card.getRank() + " " + flop2Card.getSuit());
//				
//				flop3Card = d.drawCard(cardnumber);
//				cardnumber++;
//				table.addCard(flop3Card);
//				sendMessage(flop3Card.getRank() + " " + flop3Card.getSuit());
//			}
//			else if (turn){
//				turnCard = d.drawCard(cardnumber);
//				cardnumber++;
//				table.addCard(turnCard);
//				sendMessage(turnCard.getRank() + " " + turnCard.getSuit());
//			}
//			else if (river){
//				riverCard = d.drawCard(cardnumber);
//				table.addCard(riverCard);
//				sendMessage(riverCard.getRank() + " " + riverCard.getSuit());
//			}
//			else if (gameon){
//				if (compareDecks(player1Cards, player2Cards)){
//					sendMessage("Player 1 wins");
//				}
//				else{
//					sendMessage("Player 2 wins");
//				}
//					
//				
//				gamestarted = false;
//				
//			}
//		}
//		
//		
//	}
//	
//	public boolean compareDecks(Deck first, Deck second){
//		
//		
//		return false;
//	}
//	
//	public Deck sortDeck(Deck theDeck){
//		Collections.sort(theDeck, new Comparator<Card>(){
//			public int compare(Card s1, Card s2){
//				return s1.getRank().compareTo(s2.getRank());
//			}
//		});
//		return theDeck;
//	}
//	
//
//	public boolean checkStraight(Deck theDeck){
//		Collections.sort(theDeck, new Comparator<Card>(){
//			public int compare(Card s1, Card s2){
//				return s1.getRank().compareTo(s2.getRank());
//			}
//		});
//		int straightCounter = 0;
//		for (int i = 1; i < theDeck.size() ; i++){
//			if (theDeck.get(i-1).getRank()+1 == theDeck.get(i).getRank()){
//				straightCounter++;
//			}
//			else{
//				straightCounter = 0;
//			}
//
//		}
//		if (straightCounter >= 4){
//			return true;
//		}
//		
//		return false;
//	}
//	
//	
//	public boolean checkFlush(Deck theDeck){
//		int spadesCounter = 0;
//		int diamondCounter = 0;
//		int heartsCounter = 0;
//		int clubsCounter = 0;
//
//	
//		for (int i = 0; i < theDeck.size() ; i++){
//			String tempSuit = theDeck.get(i).getSuit();
//			if (tempSuit.equals("Spades")){
//				spadesCounter++;
//			}
//			else if (tempSuit.equals("Diamonds")){
//				diamondCounter++;
//			}
//			else if (tempSuit.equals("Hearts")){
//				heartsCounter++;
//			}
//			else if (tempSuit.equals("Clubs")){
//				clubsCounter ++;
//			}
//		}
//		
//		if (spadesCounter >= 5 || diamondCounter >= 5
//			|| heartsCounter >=5 || clubsCounter >=5){
//				return true;
//			}
//		else{
//			return false;
//		}
//				
//		
//	}
//	
//	
//	public boolean checkStraightFlush(Deck theDeck){
//		if (checkStraight(theDeck) && checkFlush(theDeck)){
//			return true;
//		}
//		return false;
//	}
//	
//	public int checkFourofAKind(Deck theDeck){
//		int two = 0;
//		int three = 0;
//		int four = 0;
//		int five = 0;
//		int six = 0;
//		int seven = 0;
//		int eight = 0;
//		int nine = 0;
//		int ten = 0;
//		int jack = 0;
//		int queen = 0;
//		int king = 0;
//		int ace = 0;
//
//		for (int i = 1; i < theDeck.size() ; i++){
//			int tempRank = theDeck.get(i).getNumRank();
//			
//			if (tempRank == 2){
//				two++;
//			}
//			else if (tempRank == 3){
//				three++;
//			}
//			else if (tempRank == 4){
//				four++;
//			}
//			else if (tempRank == 5){
//				five++;
//			}
//			else if (tempRank == 6){
//				six++;
//			}
//			else if (tempRank == 7){
//				seven++;
//			}
//			else if (tempRank == 8){
//				eight++;
//			}
//			else if (tempRank == 9){
//				nine++;
//			}
//			else if (tempRank == 10){
//				ten++;
//			}
//			else if (tempRank == 11){
//				jack++;
//			}
//			else if (tempRank == 12){
//				queen++;
//			}
//			else if (tempRank == 13){
//				king++;
//			}
//			else if (tempRank == 14){
//				ace++;
//			}
//		}
//		
//		if (two >=4){
//			return 2;
//		}
//		
//		else if (three >= 4){
//			return 3;
//		}
//		
//		else if (four >= 4){
//			return 4;
//		}
//		
//		else if (five >= 4){
//			return 5;
//		}
//			
//		else if (six >= 4){
//			return 6;
//		}
//		
//		else if (seven >= 4){
//			return 7;
//		}
//		
//		else if (eight >= 4){
//			return 8;
//		}
//		
//		else if (nine >= 4){
//			return 9;
//		}
//		else if (ten >= 4){
//			return 10;
//		}
//		else if (jack >= 4){
//			return 11;
//		}
//		else if (queen >=4){
//			return 12;
//		}
//		else if (king >= 4){
//			return 13;
//		}
//		else{
//			return 0;
//		}
//	}
//
//}
