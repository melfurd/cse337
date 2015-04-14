package project;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.util.*;

import javax.swing.JButton;


public class PokerTest{
	
	private Deck d;
	private JButton winner;
	private JButton deal;
	private Deck player1Cards;
	private Deck player2Cards;
	private int cardNumber;
	private boolean dealCards = true;
	private boolean winCards = false;

	public PokerTest(){
		d = new Deck();
		d.standardFill();
		winner = new JButton("Winner");
		deal = new JButton("Deal");
		
	}

	public void deal(){
		
		d = new Deck();
		d.standardFill();
		cardNumber = 0;
		this.player1Cards = new Deck();
		this.player2Cards = new Deck();
		//1
		player1Cards.addCard(d.drawCard(cardNumber));
		cardNumber++;
		player2Cards.addCard(d.drawCard(cardNumber));
		cardNumber++;
		
		//2
		player1Cards.addCard(d.drawCard(cardNumber));
		cardNumber++;
		player2Cards.addCard(d.drawCard(cardNumber));
		cardNumber++;
		
		//3
		player1Cards.addCard(d.drawCard(cardNumber));
		cardNumber++;
		player2Cards.addCard(d.drawCard(cardNumber));
		cardNumber++;
		
		//4
		player1Cards.addCard(d.drawCard(cardNumber));
		cardNumber++;
		player2Cards.addCard(d.drawCard(cardNumber));
		cardNumber++;
		
		//5
		player1Cards.addCard(d.drawCard(cardNumber));
		cardNumber++;
		player2Cards.addCard(d.drawCard(cardNumber));
		cardNumber++;
		
		Collections.sort(player1Cards, new Comparator<Card>(){
			public int compare(Card s1, Card s2){
				return s1.getCompareToRank().compareTo(s2.getCompareToRank());
			}
		});
		
		Collections.sort(player2Cards, new Comparator<Card>(){
			public int compare(Card s1, Card s2){
				return s1.getCompareToRank().compareTo(s2.getCompareToRank());
			}
		});
		
		System.out.println("Player One");
		for (int i = 0; i < player1Cards.size() ; i++){
			 System.out.println(player1Cards.get(i).getSuit() + " " + player1Cards.get(i).getRank());
		}
		
		System.out.println("Player Two");
		for (int i = 0; i < player2Cards.size() ; i++){
			 System.out.println(player2Cards.get(i).getSuit() + " " + player2Cards.get(i).getRank());
		}
		
		
	}
	
	public void player1CardResult(){
		
	}
	
	public void player2CardResult(){
		
	}
	
	
	
	public void whoWins(){
		Collections.sort(player1Cards, new Comparator<Card>(){
			public int compare(Card s1, Card s2){
				return s1.getCompareToRank().compareTo(s2.getCompareToRank());
			}
		});
		
		Collections.sort(player2Cards, new Comparator<Card>(){
			public int compare(Card s1, Card s2){
				return s1.getCompareToRank().compareTo(s2.getCompareToRank());
			}
		});
		
		boolean firstPersonWins = compareDecks(player1Cards, player2Cards);
		
		if (firstPersonWins){
			System.out.println("first player wins");
		}
		else{
			System.out.println("2nd player wins");
		}
		
	}
	
	
	public boolean compareDecks(Deck first, Deck second){
		
		int firstPlayerRank = first.get(4).getRealNumRank();
		int secondPlayerRank = second.get(4).getRealNumRank();
		boolean firstPlayer = false;
		boolean secondPlayer = false;
		int firstPlayerValue = 0;
		int secondPlayerValue = 0;
		
		
		//Check straight flush
		
		firstPlayer = checkStraightFlush(first);
		secondPlayer = checkStraightFlush(second);
		
		if (firstPlayer && secondPlayer){
			if (firstPlayerRank > secondPlayerRank){
				System.out.println("Player 1 has flush with higher card");
				return true;

			}
			else{
				System.out.println("Player 2 has flush with higher card");
				return false;
			}
				
		}
		else if (firstPlayer){
			System.out.println("Player 1 has flush");
			return true;
		}
		else if (secondPlayer){
			System.out.println("Player 2 has flush");
			return false;
		}
		
		//Check four of a kind
		
		firstPlayerValue = checkNumOfAKind(first, 4);
		secondPlayerValue = checkNumOfAKind(second, 4);
		
		if (firstPlayerValue == 0 && secondPlayerValue == 0){
			
		}
		else if (firstPlayerValue > secondPlayerValue){
			System.out.println("Player 1 has four of a kind " + firstPlayerValue);
			return true;
		}
		else if (secondPlayerValue > firstPlayerValue){
			System.out.println("Player 2 has four of a kind " + secondPlayerValue);
			return false;
		}
		
		//Check full house
		firstPlayerValue = checkFullHouse(first);
		secondPlayerValue = checkFullHouse(second);
		if (firstPlayerValue == 0 && secondPlayerValue == 0){
			
		}
		else if (firstPlayerValue > secondPlayerValue){
			System.out.println("Player 1 has full house with 3 of a kind being" + firstPlayerValue);
			return true;
		}
		else if (secondPlayerValue > firstPlayerValue){
			System.out.println("Player 2 has full house with 3 of a kind being " + secondPlayerValue);
			return false;
		}
		
		
		//Check flush
		firstPlayer = checkFlush(first);
		secondPlayer = checkFlush(second);
		
		if (firstPlayer && secondPlayer){
			if (firstPlayerRank > secondPlayerRank){
				return true;
			}
			else{
				return false;
			}
				
		}
		else if (firstPlayer){
			return true;
		}
		else if (secondPlayer){
			return false;
		}
		
		//Check three of a kind
		
		firstPlayerValue = checkNumOfAKind(first, 3);
		secondPlayerValue = checkNumOfAKind(second, 3);
		
		if (firstPlayerValue == 0 && secondPlayerValue == 0){
			
		}
		else if (firstPlayerValue > secondPlayerValue){
			System.out.println("Player 1 has 3 of a kind being" + firstPlayerValue);
			return true;
		}
		else if (secondPlayerValue > firstPlayerValue){
			System.out.println("Player 2 has 3 of a kind being " + secondPlayerValue);
			return false;
		}
		
		
		//Check two pairs of a kind
		
		firstPlayerValue = checkTwoPair(first);
		secondPlayerValue = checkTwoPair(second);
		
		if (firstPlayerValue == 0 && secondPlayerValue == 0){
			
		}
		else if (firstPlayerValue > secondPlayerValue){
			System.out.println("Player 1 has 2 pairs with high pair of " + firstPlayerValue);
			return true;
		}
		else if (secondPlayerValue > firstPlayerValue){
			System.out.println("Player 2 has 2 pairs with high pair of " + secondPlayerValue);
			return false;
		}
		
		//Check 2 of a kind
		
		firstPlayerValue = checkNumOfAKind(first, 2);
		secondPlayerValue = checkNumOfAKind(second, 2);
		
		if (firstPlayerValue == 0 && secondPlayerValue == 0){
			
		}
		else if (firstPlayerValue > secondPlayerValue){
			System.out.println("Player 1 has 2 of a kind being" + firstPlayerValue);
			return true;
		}
		else if (secondPlayerValue > firstPlayerValue){
			System.out.println("Player 2 has 2 of a kind being " + secondPlayerValue);
			return false;
		}
		
		
		//High Card
		if (firstPlayerRank > secondPlayerRank){
			System.out.println("Player 1 has high card " + firstPlayerRank);
			return true;
		}
		else if (firstPlayerRank == secondPlayerRank){
			System.out.println("Player 1 and 2 has high card " + firstPlayerRank);
			return true;
		}
		else{
			System.out.println("Player 2 has high card " + secondPlayerRank);
			return false;
		}
			
		

	}
	
	public Deck sortDeck(Deck theDeck){
		Collections.sort(theDeck, new Comparator<Card>(){
			public int compare(Card s1, Card s2){
				return s1.getRank().compareTo(s2.getRank());
			}
		});
		return theDeck;
	}
	

	public boolean checkStraight(Deck theDeck){
		Collections.sort(theDeck, new Comparator<Card>(){
			public int compare(Card s1, Card s2){
				return s1.getRank().compareTo(s2.getRank());
			}
		});
		int straightCounter = 0;
		for (int i = 1; i < theDeck.size() ; i++){
			if (theDeck.get(i-1).getRank()+1 == theDeck.get(i).getRank()){
				straightCounter++;
			}
			else{
				straightCounter = 0;
			}

		}
		if (straightCounter >= 4){
			return true;
		}
		
		return false;
	}
	
	
	public boolean checkFlush(Deck theDeck){
		int spadesCounter = 0;
		int diamondCounter = 0;
		int heartsCounter = 0;
		int clubsCounter = 0;

	
		for (int i = 0; i < theDeck.size() ; i++){
			String tempSuit = theDeck.get(i).getSuit();
			if (tempSuit.equals("Spades")){
				spadesCounter++;
			}
			else if (tempSuit.equals("Diamonds")){
				diamondCounter++;
			}
			else if (tempSuit.equals("Hearts")){
				heartsCounter++;
			}
			else if (tempSuit.equals("Clubs")){
				clubsCounter ++;
			}
		}
		
		if (spadesCounter >= 5 || diamondCounter >= 5
			|| heartsCounter >=5 || clubsCounter >=5){
				return true;
			}
		else{
			return false;
		}
				
		
	}
	
	
	public boolean checkStraightFlush(Deck theDeck){
		if (checkStraight(theDeck) && checkFlush(theDeck)){
			return true;
		}
		return false;
	}
	
	public int checkNumOfAKind(Deck theDeck, int num){
		int two = 0;
		int three = 0;
		int four = 0;
		int five = 0;
		int six = 0;
		int seven = 0;
		int eight = 0;
		int nine = 0;
		int ten = 0;
		int jack = 0;
		int queen = 0;
		int king = 0;
		int ace = 0;

		for (int i = 0; i < theDeck.size() ; i++){
			int tempRank = theDeck.get(i).getRealNumRank();
			
			if (tempRank == 2){
				two++;
			}
			else if (tempRank == 3){
				three++;
			}
			else if (tempRank == 4){
				four++;
			}
			else if (tempRank == 5){
				five++;
			}
			else if (tempRank == 6){
				six++;
			}
			else if (tempRank == 7){
				seven++;
			}
			else if (tempRank == 8){
				eight++;
			}
			else if (tempRank == 9){
				nine++;
			}
			else if (tempRank == 10){
				ten++;
			}
			else if (tempRank == 11){
				jack++;
			}
			else if (tempRank == 12){
				queen++;
			}
			else if (tempRank == 13){
				king++;
			}
			else if (tempRank == 14){
				ace++;
			}
		}
		if (ace == num){
			return 14;
		}
		
		else if (king == num){
			return 13;
		}
		else if (queen == num){
			return 12;
		}
		else if (jack == num){
			return 11;
		}
		else if (ten == num){
			return 10;
		}
		
		else if (nine == num){
			return 9;
		}
		
		else if (eight == num){
			return 8;
		}
		
		else if (seven == num){
			return 7;
		}
		else if (six == num){
			return 6;
		}
		else if (five == num){
			return 5;
		}
		else if (four == num){
			return 4;
		}
		
		else if (three == num){
			return 3;
		}
		
		else if (two == num){
			return 2;
		}
		else{
			return 0;
		}
	}
	
	


	public int checkFullHouse(Deck deck){
		int threeOfAKind = checkNumOfAKind(deck,3);
		int twoOfAKind = checkNumOfAKind(deck,2);
		
		if (threeOfAKind == 0 || twoOfAKind == 0){
			return 0;
		}
		else{
			return threeOfAKind;
		}
	}
	
	public int checkTwoPair(Deck theDeck){
		int num = 2;
		int two = 0;
		int three = 0;
		int four = 0;
		int five = 0;
		int six = 0;
		int seven = 0;
		int eight = 0;
		int nine = 0;
		int ten = 0;
		int jack = 0;
		int queen = 0;
		int king = 0;
		int ace = 0;

		for (int i = 0; i < theDeck.size() ; i++){
			int tempRank = theDeck.get(i).getRealNumRank();
			
			if (tempRank == 2){
				two++;
			}
			else if (tempRank == 3){
				three++;
			}
			else if (tempRank == 4){
				four++;
			}
			else if (tempRank == 5){
				five++;
			}
			else if (tempRank == 6){
				six++;
			}
			else if (tempRank == 7){
				seven++;
			}
			else if (tempRank == 8){
				eight++;
			}
			else if (tempRank == 9){
				nine++;
			}
			else if (tempRank == 10){
				ten++;
			}
			else if (tempRank == 11){
				jack++;
			}
			else if (tempRank == 12){
				queen++;
			}
			else if (tempRank == 13){
				king++;
			}
			else if (tempRank == 14){
				ace++;
			}
		}
		
		int needs2 = 0;
		
		if (ace == num){
			needs2++;
		}
		
		if (king == num){
			needs2++;
		}
		if (queen == num){
			needs2++;
		}
		
		if (jack == num){
			needs2++;
		}
		
		if (ten == num){
			needs2++;
		}
		
		if (nine == num){
			needs2++;
		}
		
		if (eight == num){
			needs2++;
		}
		
		if (seven == num){
			needs2++;
		}
		
		if (six == num){
			needs2++;
		}
		
		if (five == num){
			needs2++;
		}
		
		if (four == num){
			needs2++;
		}
		
		if (three == num){
			needs2++;
		}
		
		if (two == num){
			needs2++;
		}
		
		
		
		if (needs2++ < 2){
			return 0;
		}
		else if (ace == num){
			return 14;
		}
		
		else if (king == num){
			return 13;
		}
		else if (queen == num){
			return 12;
		}
		else if (jack == num){
			return 11;
		}
		else if (ten == num){
			return 10;
		}
		
		else if (nine == num){
			return 9;
		}
		
		else if (eight == num){
			return 8;
		}
		
		else if (seven == num){
			return 7;
		}
		else if (six == num){
			return 6;
		}
		else if (five == num){
			return 5;
		}
		else if (four == num){
			return 4;
		}
		
		else if (three == num){
			return 3;
		}
		
		else if (two == num){
			return 2;
		}
		else{
			return 0;
		}

	}
}
