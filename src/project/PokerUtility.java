package project;

import java.util.Collections;
import java.util.Comparator;

/**
 * Poker Utility that can be used to compare decks to 
 * determine which deck wins, as well as compare two decks
 * Can also be used to determine if a deck meets a certain case;
 * @author Stuart Nolton and Mel Chi
 * @version 2.3 - 2015-04-18
 * @since   1.0 - 2015-03-31 
 */
public class PokerUtility {
	
	private int player1MainRank = 0;
	private int player1SecondRank = 0;
	private int player1ThirdRank = 0;
	
	private int player2MainRank = 0;
	private int player2SecondRank = 0;
	private int player2ThirdRank = 0;
	
	/**
	 * int that holds the highRank data
	 * For straights, it holds the highest value in a straight
	 * For n of a kind, it holds the highest value of n of a kind
	 */
	public int highRank1 = 0;
	/**
	 * Flush, holds highest flush value card
	 */
	public int highRank2 = 0;
	/**
	 * Holds the highest value of non importance for kicker
	 */
	public int highRank3 = 0;
	
	private boolean firstPlayer = false;
	private boolean secondPlayer = false;
	
	/**`
	 * Accessable Boolean to determine if there was a tie
	 */
	public boolean tieDeck = false;
	/**
	 * Acccessable message to grab to display if necessary
	 */
	public String cardMessage = "";
	Deck first;
	Deck second;
	Deck temporary;
	
	Deck firstTempDeck = new Deck();
	Deck secondTempDeck = new Deck();
	Deck tempDeck = new Deck();
	
	/**
	 * Default constructor, that way you can use this class for methods
	 */
	public PokerUtility(){
		resetUtility();
	}
	
	/**
	 * Constructor that takes in two decks of cards, and will be able to
	 * manipulate the data.
	 * @param first First Deck
	 * @param second Second Deck
	 */
	public PokerUtility(Deck first, Deck second){
		this.first = first;
		this.second = second;
	}
	
	/**
	 * Reset Utility
	 */
	private void resetUtility(){
		this.player1MainRank = 0;
		this.player1SecondRank = 0;
		this.player1ThirdRank = 0;
		this.player2MainRank = 0;
		this.player2SecondRank = 0;
		this.player2ThirdRank = 0;
		this.firstPlayer = false;
		this.secondPlayer = false;
		this.tieDeck = false;
		this.firstTempDeck = new Deck();
		this.secondTempDeck = new Deck();
		this.tempDeck = new Deck();
		resetCardRank();
	}
	
	/**
	 * Reset utility to put back 2 decks
	 * @param first First Deck
	 * @param second Second Deck
	 */
	public void resetUtility(Deck first, Deck second){
		resetUtility();
		this.first = sortDeck(first);
		this.second = sortDeck(second);
	}
	
	/**
	 * resets high rank
	 */
	public void resetCardRank(){
		this.highRank1 = 0;
		this.highRank2 = 0;
		this.highRank3 = 0;
	}
		
	/** public method to sort decks
	 * 
	 * @param theDeck The deck to sort
	 * @return The deck sorted
	 */
	public Deck sortDeck(Deck theDeck){
		Collections.sort(theDeck, new Comparator<Card>(){
			public int compare(Card s1, Card s2){
				return s1.getCompareToRank().compareTo(s2.getCompareToRank());
			}
		});
		return theDeck;
	}
	

	/**
	 * Compares two decks starting from straightflush, 4 of a kind, fullhouse
	 * flush, three of a kind, two pairs, two of a kind, high card
	 * @param first First deck
	 * @param second Second Deck
	 * @return true if first deck wins, true if a tie and the object.tieDeck is true
	 */
	public boolean compareDecks(Deck first, Deck second){
		resetUtility();
		
		first = sortDeck(first);
		second = sortDeck(second);
		
		//STRAIGHT FLUSH
		//System.out.println("StraightFlush");
		this.firstPlayer = checkStraightFlush(first);
		this.player1MainRank = this.highRank1;
		
		this.secondPlayer = checkStraightFlush(second);
		this.player2MainRank = this.highRank1;
		
		
		// IF BOTH HAVE A STRAIGHT FLUSH NEED TO COMPARE RANK
		if (firstPlayer && secondPlayer){
			if (player1MainRank > player2MainRank){
				if (player1MainRank == 14){
					this.cardMessage = "Player wins with a " + checkFlush(first) + " royal flush!";
				}
				else{
					this.cardMessage = "Player wins with a straight flush with high card of " + player1MainRank;
				}
				return true;
			}
			else if (player1MainRank < player2MainRank){
				if (player2MainRank == 14){
					this.cardMessage = "Player wins with a " + checkFlush(second) + " royal flush!";
				}
				else{
					this.cardMessage = "Player wins with a straight flush with high card of " + player2MainRank;
				}
				return false;
			}
			else{
				this.cardMessage = "Player wins with a straight flush with high card of " + player2MainRank;
				this.tieDeck = true;
				return true;
			}
		}
		else if (firstPlayer){ //ONLY FIRST PLAYER HAS
			if (player1MainRank == 14){
				this.cardMessage = "Player wins with a " + checkFlush(first) + " royal flush!";
			}
			else{
				this.cardMessage = "Player wins with a straight flush with high card of " + player1MainRank;
			}
			return true;
		}
		else if (secondPlayer){ //ONLY SECOND PLAYER HAS
			if (player2MainRank == 14){
				this.cardMessage = "Player wins with a " + checkFlush(second) + " royal flush!";
			}
			else{
				this.cardMessage = "Player wins with a straight flush with high card of " + player2MainRank;
			}
			return false;
		}
		
		
		// Check 4 of a kind
		//System.out.println("4");
		this.player1MainRank = checkNumOfAKind(first, 4, 0);
		this.player2MainRank = checkNumOfAKind(second, 4, 0);
		
		if (player1MainRank == 0 && player2MainRank == 0){
			
		}
		else{
			if (player1MainRank > player2MainRank){
				this.cardMessage = "Player wins with a 4 " + convertRealNumRankToRank(player1MainRank) + "s.";
				return true;
			}
			else if (player1MainRank < player2MainRank){
				this.cardMessage = "Player wins with a 4 " + convertRealNumRankToRank(player2MainRank) + "s.";
				return false;
			}
			else{
				this.player1ThirdRank = getHighCardNoMatter(player1MainRank, 1, first);
				this.player2ThirdRank = getHighCardNoMatter(player2MainRank, 1, second);
				if (player1ThirdRank > player2ThirdRank){
					this.cardMessage = "Player wins with a 4 " 
							 + convertRealNumRankToRank(player1MainRank) + "with " + convertRealNumRankToRank(player1ThirdRank) + " kicker.";
					return true;
				}
				else if (player1ThirdRank < player2ThirdRank){
					this.cardMessage = "Player wins with a 4 " 
							 + convertRealNumRankToRank(player2MainRank) + "with " + convertRealNumRankToRank(player2ThirdRank) + " kicker.";
					return false;
				}
				else{
					this.cardMessage = "Player tie with a 4 " 
							 + convertRealNumRankToRank(player2MainRank) + "with " + convertRealNumRankToRank(player2ThirdRank) + " kicker.";
					tieDeck = true;
					return true;
				}
			}
		}
		
		//Full House
		//System.out.println("full");
		this.player1MainRank = checkFullHouse(first);
		this.player1SecondRank = this.highRank2; 
		this.player2MainRank = checkFullHouse(second);
		this.player2SecondRank = this.highRank2; 
		
		if (player1MainRank == 0 && player2MainRank == 0){
			
		}
		else{
			if (player1MainRank > player2MainRank){
				this.cardMessage = "Player wins with a full house with 3 " + convertRealNumRankToRank(player1MainRank) + " and 2" + convertRealNumRankToRank(player1SecondRank);
				return true;
			}
			else if (player1MainRank < player2MainRank){
				this.cardMessage = "Player wins with a full house with 3 " + convertRealNumRankToRank(player2MainRank) + " and 2" + convertRealNumRankToRank(player2SecondRank);
				return false;
			}
			else{
				if (player1SecondRank > player2SecondRank){
					this.cardMessage = "Player wins with a full house with 3 " 
								+ convertRealNumRankToRank(player1MainRank)
								+ " and 2" + convertRealNumRankToRank(player1SecondRank);
					return true;
				}
				else if (player1SecondRank < player2SecondRank){
					this.cardMessage = "Player wins with a full house with  " 
							+ convertRealNumRankToRank(player2MainRank)
							+ " and 2" + convertRealNumRankToRank(player2SecondRank);
					return false;
				}
				else{
					this.cardMessage = "Players tie with full house of " 
							+ convertRealNumRankToRank(player2MainRank)
							+ " and " + convertRealNumRankToRank(player2SecondRank);
					tieDeck = true;
					return true;
				}
			}
		}
		
		//CHECK FLUSH
		String temp1 = "";
		String temp2 = "";
		temp1 = checkFlush(first);
		temp2 = checkFlush(second);
		
		if (temp1.equals("None") && temp2.equals("None")){
			
		}
		else{
			temp1 = checkFlush(first);
			this.firstTempDeck = tempDeck;
			player1MainRank = highRank2;
			
			temp2 = checkFlush(second);
			this.secondTempDeck = tempDeck;
			player2MainRank = highRank2;
			
			if (!temp1.equals("None") && temp2.equals("None")){
				this.cardMessage = "Player wins with a flush of " + temp1;
				return true;
			}
			else if (temp1.equals("None") && !temp2.equals("None")){
				this.cardMessage = "Player wins with a flush of " + temp2;
				return false;
				
			}
			else{
				for (int i =0; i < 5; i++ ){
					if(firstTempDeck.get(i).getRealNumRank() > secondTempDeck.get(i).getRealNumRank()){
						this.cardMessage = "Player wins with a flush of " + temp1
								+ "with higher non similar card of" + firstTempDeck.get(i).getRealNumRank();
						return true;
					}
					else if (firstTempDeck.get(i).getRealNumRank() < secondTempDeck.get(i).getRealNumRank()){
						this.cardMessage = "Player wins with a flush of " + temp1
								+ "with higher non similar card of" + secondTempDeck.get(i).getRealNumRank();
						return false;
					}

				}
				
				//All the same then its a tie
				this.cardMessage = "Players tie with flush of " + temp1;
				tieDeck = true;
				return true;
				
			}
		}
		
		
		//Check Straight
		this.firstPlayer = checkStraight(first);
		this.player1MainRank = highRank1;
		this.secondPlayer = checkStraight(second);
		this.player2MainRank = highRank1;
		
		if (firstPlayer && secondPlayer){
			if (player1MainRank > player2MainRank){
				this.cardMessage = "Player wins with a straight high card of " + convertRealNumRankToRank(player1MainRank);
				return true;
			}
			else if (player1MainRank < player2MainRank){
				this.cardMessage = "Player wins with a straight high card of " + convertRealNumRankToRank(player2MainRank);
				return false;
			}
			else{
				this.cardMessage = "Player wins with a straight high card of " + convertRealNumRankToRank(player1MainRank);
				this.tieDeck = true;
				return true;
			}
		}
		else if (firstPlayer){
			this.cardMessage = "Player wins with a straight high card of " + convertRealNumRankToRank(player1MainRank);
			return true;
		}
		else if (secondPlayer){
			this.cardMessage = "Player wins with a straight high card of " + convertRealNumRankToRank(player2MainRank);
			return false;
		}
		
		//check 3 of a kind
		
		this.player1MainRank = checkNumOfAKind(first, 3, 0);
		this.player2MainRank = checkNumOfAKind(second, 3, 0);
		
		if (player1MainRank == 0 && player2MainRank == 0){
			
		}
		else{
			if (player1MainRank > player2MainRank){
				this.cardMessage = "Player wins with a 3 of kind with "
			+ convertRealNumRankToRank(player1MainRank);
				return true;
			}
			else if (player1MainRank < player2MainRank){
				this.cardMessage = "Player wins with a 3 of a kind with "
						+ convertRealNumRankToRank(player2MainRank);
				return false;
			}
			else{
				this.firstTempDeck = new Deck();
				this.secondTempDeck = new Deck();
				
				for (int i = first.size()-1; i >=0 ; i--){
					if (first.get(i).getNumRank() != player1MainRank){
						firstTempDeck.add(first.get(i));
					}
				}
				for (int i = second.size()-1; i>=0 ; i--){
					if (second.get(i).getNumRank() != player2MainRank){
						secondTempDeck.add(first.get(i));
					}
				}
				
				for (int i =0; i < 2; i++ ){
					if(firstTempDeck.get(i).getRealNumRank() > secondTempDeck.get(i).getRealNumRank()){
						this.cardMessage = "Player wins with a 3 of a kind with " 
								+ convertRealNumRankToRank(player1MainRank)
								+ "with non same kicker value of " + firstTempDeck.get(i).getRealNumRank();
						return true;
					}
					else if (firstTempDeck.get(i).getRealNumRank() < secondTempDeck.get(i).getRealNumRank()){
						this.cardMessage = "Player wins with a 3 of a kind with " 
								+ convertRealNumRankToRank(player2MainRank)
								+ "with non same kicker value of " + secondTempDeck.get(i).getRealNumRank();
						return false;
					}

				}
				
				//All the same then its a tie
				this.cardMessage = "Players tie 3 of a kind" + player2MainRank;
				tieDeck = true;
				return true;

			}
		}
		
		//Check 2 pair
		
		this.player1MainRank = checkTwoPair(first);
		this.player1SecondRank = this.highRank2;
		this.player1ThirdRank = this.highRank3;
		this.player2MainRank = checkTwoPair(second);
		this.player2SecondRank = this.highRank2;
		this.player2ThirdRank = this.highRank3;
		
		if (player1MainRank == 0 && player2MainRank == 0){
			
		}
		else{
			if (player1MainRank > player2MainRank){
				this.cardMessage = "Player wins with a 2 pairs with 2 " 
						+ convertRealNumRankToRank(player1MainRank) + " and 2 "
						+ convertRealNumRankToRank(player1SecondRank);
				return true;
			}
			else if (player1MainRank < player2MainRank){
				this.cardMessage = "Player wins with a 2 pairs with 2 " 
						+ convertRealNumRankToRank(player2MainRank) + " and 2 "
						+ convertRealNumRankToRank(player2SecondRank);
				return false;
			}
			else{
				if (player1SecondRank > player2SecondRank){
					this.cardMessage = "Player wins with a 2 pairs of kind with " 
							+ convertRealNumRankToRank(player1MainRank) + " and higher 2nd pair of " 
							+ convertRealNumRankToRank(player1SecondRank);
					return true;
				}
				else if (player1SecondRank < player2SecondRank){
					this.cardMessage = "Player wins with a 2 pairs of kind with "
					+ convertRealNumRankToRank(player2MainRank) + " and higher 2nd pair of " 
					+ convertRealNumRankToRank(player2SecondRank);
					return false;
				}
				else{
					if (player1ThirdRank > player2ThirdRank){
						this.cardMessage = "Player wins with a 2 pairs of kind with " 
								+ convertRealNumRankToRank(player1MainRank) + " and higher kicker";
						return true;
					}
					else if (player1ThirdRank < player2ThirdRank){
						this.cardMessage = "Player wins with a 2 pairs of a kind with " + convertRealNumRankToRank(player2MainRank)  + " and higher kicker";
						return false;
					}
					else{
						this.cardMessage = "Players with 2 players having 2 pairs plus same kicker. " 
					+ convertRealNumRankToRank(player2MainRank);
						tieDeck = true;
						return true;
					}
				}
			}
		}
		
		
		//check 2 of a kind
		
		this.player1MainRank = checkNumOfAKind(first, 2, 0);
		this.player2MainRank = checkNumOfAKind(second, 2, 0);
		
		if (player1MainRank == 0 && player2MainRank == 0){
			
		}
		else{
			if (player1MainRank > player2MainRank){
				this.cardMessage = "Player wins with a 2 of kind with "
						+ convertRealNumRankToRank(player1MainRank) + "s.";
				return true;
			}
			else if (player1MainRank < player2MainRank){
				this.cardMessage = "Player wins with a 2 of kind with "
						+ convertRealNumRankToRank(player2MainRank) + "s.";
				return false;
			}
			else{

				this.firstTempDeck = new Deck();
				this.secondTempDeck = new Deck();
				
				for (int i = first.size()-1; i >=0 ; i--){
					if (first.get(i).getNumRank() != player1MainRank){
						firstTempDeck.add(first.get(i));
					}
				}
				for (int i = second.size()-1; i>=0 ; i--){
					if (second.get(i).getNumRank() != player2MainRank){
						secondTempDeck.add(first.get(i));
					}
				}
				
				for (int i =0; i < 3; i++ ){
					if(firstTempDeck.get(i).getRealNumRank() > secondTempDeck.get(i).getRealNumRank()){
						this.cardMessage = "Player wins with a 2 of a kind with " 
								+ convertRealNumRankToRank(player1MainRank)
								+ "with non same kicker value of " + firstTempDeck.get(i).getRealNumRank();
						return true;
					}
					else if (firstTempDeck.get(i).getRealNumRank() < secondTempDeck.get(i).getRealNumRank()){
						this.cardMessage = "Player wins with a 2 of a kind with " 
								+ convertRealNumRankToRank(player2MainRank)
								+ "with non same kicker value of " + secondTempDeck.get(i).getRealNumRank();
						return false;
					}

				}
				
				//All the same then its a tie
				this.cardMessage = "Players tie 2 of a kind" + player2MainRank;
				tieDeck = true;
				return true;
				

			}
		}
		
		
		//check high card
		int deckFirstSize = first.size()-1;
		int deckSecondSize = first.size()-1;

		
		if (first.get(deckFirstSize).getRealNumRank() > second.get(deckSecondSize).getRealNumRank()){
			this.cardMessage = "Player wins with a high card of " + first.get(deckFirstSize).getRealNumRank();
			return true;
		}
		else if (first.get(deckFirstSize).getRealNumRank() < second.get(deckSecondSize).getRealNumRank()){
			this.cardMessage = "Player wins with a high card of " + second.get(deckSecondSize).getRealNumRank();
			return false;
		}
		else{
			player1MainRank = 0;
			player2MainRank = 0;
			
			
			for (int i = 0; i < first.size() ; i++){
				if ( first.get(i).getRealNumRank() > (second.get(i).getRealNumRank()) ){
					this.cardMessage = "Player wins with a highest non same card of " + first.get(i).getRealNumRank();
					return true;
				}
				else if (first.get(i).getRealNumRank() < (second.get(i).getRealNumRank())) {
					this.cardMessage = "Player wins with a highest non same card of " + second.get(i).getRealNumRank();
					return false;
				}
				
			}
		}
			
		tieDeck = true;
		this.cardMessage = ("Cards are tied");
		return true;	

	}
		
	/**
	 * Checks if it is a Straight Flush
	 * @param theDeck the deck to analyze
	 * @return Returns true if is a straight flush, false if it is not.
	 */
	public boolean checkStraightFlush(Deck theDeck){
		//resetUtility();
		String flush = ""; 
		flush =  checkFlush(theDeck);
		
		if (flush.equals("None")){
			return false;
		}
		
		Deck checker = new Deck();
		
		
		for(int i = 0; i < theDeck.size(); i++){
			if (theDeck.get(i).getSuit().equals(flush)){
				checker.add(theDeck.get(i));
			}
		}
		
		boolean checkIfStraight = checkStraight(checker);
		
		if (checkIfStraight){
			tempDeck = checker;
			return true;
		}
		
		return false;
	}
	
	/**
	 * Checks if you have the n number of kind.
	 * @param theDeck Input deck
	 * @param num the number of occurances 
	 * @param ignoreValue, input an ignore value, useful when looking for two pair or full house
	 * @return return the int of the highest value, returns 0 if there are no n of a kind
	 */
	public int checkNumOfAKind(Deck theDeck, int num, int ignoreValue){
		//resetUtility();
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
		if (ace >= num && ignoreValue != 14){
			this.highRank1 = 14;
			return 14;
		}
		
		else if (king >= num && ignoreValue != 13){
			this.highRank1 = 13;
			return 13;
		}
		else if (queen >= num && ignoreValue != 12){
			this.highRank1 = 12;
			return 12;
		}
		else if (jack >= num && ignoreValue != 11){
			this.highRank1 = 11;
			return 11;
		}
		else if (ten == num && ignoreValue != 10){
			this.highRank1 = 10;
			return 10;
		}
		
		else if (nine >= num && ignoreValue != 9){
			this.highRank1 = 9;
			return 9;
		}
		
		else if (eight >= num && ignoreValue != 8){
			this.highRank1 = 8;
			return 8;
		}
		
		else if (seven >= num && ignoreValue != 7){
			this.highRank1 = 7;
			return 7;
		}
		else if (six >= num && ignoreValue != 6){
			this.highRank1 = 6;
			return 6;
		}
		else if (five >= num && ignoreValue != 5){
			this.highRank1 = 5;
			return 5;
		}
		else if (four >= num && ignoreValue != 4){
			this.highRank1 = 4;
			return 4;
		}
		
		else if (three >= num && ignoreValue != 3){
			this.highRank1 = 3;
			return 3;
		}
		
		else if (two >= num && ignoreValue != 2){
			this.highRank1 = 2;
			return 2;
		}
		else{
			this.highRank1 = 0;
			return 0;
		}
	}
	
	/**
	 * Check if a deck has a full house
	 * @param deck The deck you want to check for a full house
	 * @return returns the int value of the 3 of a kind, if it returns 0,
	 * There is no full house
	 */
	public int checkFullHouse(Deck deck){
		//resetUtility();
		int fullHouse1Check = checkNumOfAKind(deck,3, 0);
		if (fullHouse1Check == 0){
			return 0;
		}
		
		int fullHouse2Check = checkNumOfAKind(deck,2, fullHouse1Check);
		
		if (fullHouse2Check == 0){
			return 0;
		}
		else{
			this.highRank1 = fullHouse1Check;
			this.highRank2 = fullHouse2Check;
			return highRank1;
		}
	}
	
	/**
	 * Checks if the deck has a flush
	 * @param theDeck The deck you want to check for a flush
	 * @return Returns the string of the type of flush, returns "N/A" if no flush
	 */
	public String checkFlush(Deck theDeck){
		int spadesCounter = 0;
		int diamondCounter = 0;
		int heartsCounter = 0;
		int clubsCounter = 0;
		
		//theDeck = sortDeck(theDeck);

	
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
		
		int exitCounter = 0;
		tempDeck = new Deck();
		if (spadesCounter >= 5){
			for (int i = theDeck.size()-1; i >=0 ; i--){
				String tempSuit = theDeck.get(i).getSuit();
				if (tempSuit.equals("Spades")){
					this.highRank2 = highRank2 + theDeck.get(i).getRealNumRank();
					tempDeck.add(theDeck.get(i));
					exitCounter++;
					if (exitCounter >= 5){
						break;
					}
				}
			}
			return "Spades";
		}
		else if (diamondCounter >= 5){
			for (int i = theDeck.size()-1; i >=0 ; i--){
				String tempSuit = theDeck.get(i).getSuit();
				if (tempSuit.equals("Diamonds")){
					this.highRank2 = highRank2 + theDeck.get(i).getRealNumRank();
					tempDeck.add(theDeck.get(i));
					exitCounter++;
					if (exitCounter >= 5){
						break;
					}
				}
			}
			return "Diamonds";
		}
		else if (heartsCounter >= 5){
			for (int i = theDeck.size()-1; i >=0 ; i--){
				String tempSuit = theDeck.get(i).getSuit();
				if (tempSuit.equals("Hearts")){
					this.highRank2 = highRank2 + theDeck.get(i).getRealNumRank();
					tempDeck.add(theDeck.get(i));
					exitCounter++;
					if (exitCounter >= 5){
						break;
					}
				}
			}
			return "Hearts";
		}
		else if (clubsCounter >= 5){
			for (int i = theDeck.size()-1; i >=0 ; i--){
				String tempSuit = theDeck.get(i).getSuit();
				if (tempSuit.equals("Clubs")){
					this.highRank2 = highRank2 + theDeck.get(i).getRealNumRank();
					tempDeck.add(theDeck.get(i));
					exitCounter++;
					if (exitCounter >= 5){
						break;
					}
				}
			}
			return "Clubs";
		}
		else{
			return "None";
		}
				
		
	}
	
	/**
	 * Checks if the deck has a straight
	 * @param theDeck The deck you want to check if it has a straight
	 * @return returns true if there is a straight
	 */
	public boolean checkStraight(Deck theDeck){
		//resetUtility();
		theDeck = sortDeck(theDeck);
		boolean hasStraight = false;
		int straightCounter = 0;
		//theDeck = sortDeck(theDeck);

		//A-5 check
		
		if (theDeck.get(theDeck.size()-1).getNumericalRank() == 14 && theDeck.get(0).getNumericalRank() == 2){
			for (int i = 1; i < theDeck.size() ; i++){
				if (theDeck.get(i-1).getNumericalRank() == theDeck.get(i).getNumericalRank()){
					
				}
				else if (theDeck.get(i-1).getNumericalRank()+1 == theDeck.get(i).getNumericalRank()){
					straightCounter++;
					if (straightCounter >= 3){
						this.highRank1 = theDeck.get(i).getNumericalRank();
						hasStraight = true;
					}
				}
				else{
					straightCounter = 0;
				}
			}
			
			if (hasStraight){
				return true;
			}
		}
		
		straightCounter = 0;
		
		for (int i = 1; i < theDeck.size() ; i++){
			if (theDeck.get(i-1).getNumericalRank() == theDeck.get(i).getNumericalRank()){
				
			}
			else if (theDeck.get(i-1).getNumericalRank()+1 == theDeck.get(i).getNumericalRank()){
				straightCounter++;
				if (straightCounter >= 4){
					this.highRank1 = theDeck.get(i).getNumericalRank();
					hasStraight = true;
				}
			}
			else{
				straightCounter = 0;
			}

		}
		if (hasStraight){
			return true;
		}
		
		return false;
	}
	
	/**
	 * Checks if has two pairs
	 * @param theDeck The deck
	 * @return highest pair,  highRank2 will hold the 2nd pair value
	 * and highRank3 will return the highest kicker
	 */
	public int checkTwoPair(Deck theDeck){
		//resetUtility();
		int value1 = checkNumOfAKind(theDeck,2, 0);
		if (value1 == 0){
			return 0;
		}
		
		int value2 = checkNumOfAKind(theDeck,2, value1);
		
		if (value2 == 0){
			return 0;
		}
		else{
			this.highRank1 = value1;
			this.highRank2 = value2;
			this.highRank3 = getHighCardNoMatter(value1, value2, theDeck);
			return highRank1;
		}
		
		
	}
	
	/**
	 * Gets the highest card that doesn't matter for n number of cards and 2 pairs
	 * @param ignore1 the value of the card
	 * @param ignore2 set as 0 or 1 if you do not need it
	 * @param deckGetHighCard The deck you want to find the high card in
	 * @return the value in a int of the highest rank of card
	 */
	public int getHighCardNoMatter(int ignore1, int ignore2, Deck deckGetHighCard){
		int valueToReturn = 0;
		for (int i = deckGetHighCard.size()-1 ; i >= 0; i--){
			if (deckGetHighCard.get(i).getNumericalRank() != ignore1 && deckGetHighCard.get(i).getNumericalRank() != ignore2){
				valueToReturn = deckGetHighCard.get(i).getNumericalRank();
				break;
			}
		}
		return valueToReturn;
	}
	
	/**
	 * Converts the Rank to a String. i.e. input is 0, and will return "2";
	 * If you have the value of RealNumRank of cards, use the convertRealNumRanktoRank method
	 * @param numRank, value of cards from 0 to 12
	 * @return Rank from "2" to "Ace", if the rank does not exist, it will return "N/A"
	 */
	public String convertNumRankToRank(int numRank){
		switch(numRank){
		case 0:
			return "2";
		case 1:
			return "3";
		case 2:
			return "4";
		case 3:
			return "5";
		case 4:
			return "6";
		case 5:
			return "7";
		case 6:
			return "8";
		case 7:
			return "9";
		case 8:
			return "10";
		case 9:
			return "Jack";
		case 10:
			return "Queen";
		case 11:
			return "King";
		case 12:
			return "Ace";
		default:
			return "N/A";
		}
	}
	
	/**
	 * Converts the Rank to a String. i.e. input is 2, and will return "2";
	 * If you have the value of NumRank of cards (i.e input 0 means "2", use the convertNumRanktoRank method
	 * @param realNumRank, value of cards from 0 to 12
	 * @return Rank Returns the rank as a string
	 */
	public String convertRealNumRankToRank(int realNumRank){
		return convertNumRankToRank(realNumRank-2);
	}
	
	/**
	 * Returns num Rank from rank
	 * @param rank The rank in a string
	 * @return Returns the Num Rank from given rank, will return -1 if rank does not exist
	 */
	public int convertRankToNumRank(String rank){
		switch(rank){
		case "2":
		case "3":
		case "4":
		case "5":
		case "6":
		case "7":
		case "8":
		case "9":
		case "10":
			int hello = Integer.parseInt(rank)-2;
			return hello;
		case "Jack":
			return 9;
		case "Queen":
			return 10;
		case "King":
			return 11;
		case "Ace":
			return 12;
		default:
			return -1;
		}
	}
	
	/**
	 * Returns rank to real num rank
	 * @param rank Rank
	 * @return Returns the Real Num Rank from given rank, will return -1 if rank does not exist
	 */
	public int convertRankToRealNumRank(String rank){
		int temp = convertRankToNumRank(rank);
		if (temp == -1){
			return -1;
		}
		else{
			return temp+2;
		}
	}

	
	
}

