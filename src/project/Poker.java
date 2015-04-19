package project;
import java.awt.event.ActionEvent;
import java.util.*;

import javax.swing.JButton;


/**
 * Original poker game, has a few bugs, fixed mostly in PokerUtil and Poker2
 * @author Mel and Stuart
 *
 */
public class Poker implements CardGame{
	
	private Deck d;
	private JButton deal;
	private JButton winner;
	private Deck player1Cards;
	private Deck player2Cards;
	private int cardNumber;
	private boolean dealCards = true;
	private boolean winCards = false;
	private String message;

	/**
	 * Constructor that creates 2 decks
	 */
	public Poker(){
		d = new Deck();
		d.standardFill();
		winner = new JButton("Winner");
		deal = new JButton("Deal");
		this.player1Cards = new Deck();
		this.player2Cards = new Deck();
		
	}

	StringBuilder stringBuilder = new StringBuilder();
	@Override
	public void ActionListener(ActionEvent e) {
		if(e.getSource()== deal && dealCards){
			this.player1Cards = new Deck();
			this.player2Cards = new Deck();
			clearMessage();
			stringBuilder = new StringBuilder();
			d = new Deck();
			d.standardFill();
			cardNumber = 0;
			//1
			player1Cards.addCard(d.drawCard(0));
			player2Cards.addCard(d.drawCard(0));
			
			//2
			player1Cards.addCard(d.drawCard(0));
			player2Cards.addCard(d.drawCard(0));
			//3
			player1Cards.addCard(d.drawCard(0));
			player2Cards.addCard(d.drawCard(0));
			
			//4
			player1Cards.addCard(d.drawCard(0));
			player2Cards.addCard(d.drawCard(0));
			
			//5
			player1Cards.addCard(d.drawCard(0));
			player2Cards.addCard(d.drawCard(0));
			
			dealCards = false;
			winCards = true;
		}
		else if (e.getSource() == winner && winCards){

			whoWins();
			dealCards = true;
			winCards = false;
			
		}
		

	}
	

	@Override
	public ArrayList<Card> getCardsOnTable() {
		return theDeck;
	}
	
	Deck theDeck = new Deck();
	
	
	@Override
	public ArrayList<JButton> getActions() {
		ArrayList<JButton> actions = new ArrayList<JButton>();
		actions.add(deal);
		actions.add(winner);
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

	@Override
	public void checkForWin() {
		
	}
	
	/**
	 * Updates card count of each players deck
	 */
	public void updateCardCount(){
	}
	
	/* (non-Javadoc)
	 * @see CardGame#usesHand()
	 */
	@Override
	public boolean usesHand() {
		return true;
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
		String theHand ="";
		if(x==0){
			for(int y=0;y<player1Cards.size();y++){
				theHand = theHand+player1Cards.get(y).getCard()+"\n";
			}
		}
		else if(x==1){
			for(int y=0;y<player2Cards.size();y++){
				theHand = theHand+player2Cards.get(y).getCard()+"\n";
			}
		}
		else{
			return null;
		}
		return theHand;
	}
	
	/**
	 * Determines who wins and gives the string
	 */
	public void whoWins(){
		
		stringBuilder = new StringBuilder();
		
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
			stringBuilder.append("first player wins \n");
		}
		else{
			stringBuilder.append("2nd player wins \n");
		}
		
		sendMessage(stringBuilder.toString());
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
				stringBuilder.append("Player 1 has flush with higher card\n");
				return true;

			}
			else{
				stringBuilder.append("Player 2 has flush with higher card\n");
				return false;
			}
				
		}
		else if (firstPlayer){
			stringBuilder.append("Player 1 has flush\n");
			return true;
		}
		else if (secondPlayer){
			stringBuilder.append("Player 2 has flush\n");
			return false;
		}
		
		//Check four of a kind
		
		firstPlayerValue = checkNumOfAKind(first, 4);
		secondPlayerValue = checkNumOfAKind(second, 4);
		
		if (firstPlayerValue == 0 && secondPlayerValue == 0){
			
		}
		else if (firstPlayerValue > secondPlayerValue){
			stringBuilder.append("Player 1 has four of a kind " + firstPlayerValue + "\n");
			return true;
		}
		else if (secondPlayerValue > firstPlayerValue){
			stringBuilder.append("Player 2 has four of a kind " + secondPlayerValue + "\n");
			return false;
		}
		
		//Check full house
		firstPlayerValue = checkFullHouse(first);
		secondPlayerValue = checkFullHouse(second);
		if (firstPlayerValue == 0 && secondPlayerValue == 0){
			
		}
		else if (firstPlayerValue > secondPlayerValue){
			stringBuilder.append("Player 1 has full house with 3 of a kind being" + firstPlayerValue + "\n");
			return true;
		}
		else if (secondPlayerValue > firstPlayerValue){
			stringBuilder.append("Player 2 has full house with 3 of a kind being " + secondPlayerValue + "\n");
			return false;
		}
		
		
		//Check flush
		firstPlayer = checkFlush(first);
		secondPlayer = checkFlush(second);
		
		if (firstPlayer && secondPlayer){
			if (firstPlayerRank > secondPlayerRank){
				stringBuilder.append("Player 1 wins with a higher flush\n");
				return true;
			}
			else{
				return false;
			}
				
		}
		else if (firstPlayer){
			stringBuilder.append("Player 1 wins with a flush\n");
			return true;
		}
		else if (secondPlayer){
			stringBuilder.append("Player 2 wins with a flush\n");
			return false;
		}
		
		//Check three of a kind
		
		firstPlayerValue = checkNumOfAKind(first, 3);
		secondPlayerValue = checkNumOfAKind(second, 3);
		
		if (firstPlayerValue == 0 && secondPlayerValue == 0){
			
		}
		else if (firstPlayerValue > secondPlayerValue){
			stringBuilder.append("Player 1 has 3 of a kind being" + firstPlayerValue + "\n");
			return true;
		}
		else if (secondPlayerValue > firstPlayerValue){
			stringBuilder.append("Player 2 has 3 of a kind being " + secondPlayerValue + "\n");
			return false;
		}
		
		
		//Check two pairs of a kind
		
		firstPlayerValue = checkTwoPair(first);
		secondPlayerValue = checkTwoPair(second);
		
		if (firstPlayerValue == 0 && secondPlayerValue == 0){
			
		}
		else if (firstPlayerValue > secondPlayerValue){
			stringBuilder.append("Player 1 has 2 pairs with high pair of " + firstPlayerValue + "\n");
			return true;
		}
		else if (secondPlayerValue > firstPlayerValue){
			stringBuilder.append("Player 2 has 2 pairs with high pair of " + secondPlayerValue + "\n");
			return false;
		}
		
		//Check 2 of a kind
		
		firstPlayerValue = checkNumOfAKind(first, 2);
		secondPlayerValue = checkNumOfAKind(second, 2);
		
		if (firstPlayerValue == 0 && secondPlayerValue == 0){
			
		}
		else if (firstPlayerValue > secondPlayerValue){
			stringBuilder.append("Player 1 has 2 of a kind being" + firstPlayerValue + "\n");
			return true;
		}
		else if (secondPlayerValue > firstPlayerValue){
			stringBuilder.append("Player 2 has 2 of a kind being " + secondPlayerValue + "\n");
			return false;
		}
		else if (firstPlayerValue == secondPlayerValue){
			stringBuilder.append("Tie of a kind being " + secondPlayerValue + "\n");
			return false;
		}
		
		
		//High Card
		if (firstPlayerRank > secondPlayerRank){
			stringBuilder.append("Player 1 has high card " + firstPlayerRank + "\n");
			return true;
		}
		else if (firstPlayerRank == secondPlayerRank){
			stringBuilder.append("Player 1 and 2 has high card tie" + firstPlayerRank + "\n");
			return true;
		}
		else{
			stringBuilder.append("Player 2 has high card " + secondPlayerRank + "\n");
			return false;
		}
			
		

	}
	
	public Deck sortDeck(Deck theDeck){
		Collections.sort(theDeck, new Comparator<Card>(){
			public int compare(Card s1, Card s2){
				return s1.getCompareToRank().compareTo(s2.getCompareToRank());
			}
		});
		return theDeck;
	}
	

	public boolean checkStraight(Deck theDeck){
		Collections.sort(theDeck, new Comparator<Card>(){
			public int compare(Card s1, Card s2){
				return s1.getCompareToRank().compareTo(s2.getCompareToRank());
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
	
	