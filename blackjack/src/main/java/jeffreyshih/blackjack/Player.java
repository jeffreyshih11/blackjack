package jeffreyshih.blackjack;

import java.util.ArrayList;

public class Player {

	private String name;
	private ArrayList<Card> hand;
	private int total;
	private int numWins;
	
	public Player(String name){
		this.name = name;
		hand = new ArrayList<Card>();
		total = 0;
		numWins = 0;
	}
	
	public boolean addToHand(Card card){
		return hand.add(card);
	}
	
	public boolean updateTotal(){
		total = 0;
		for(Card card: hand){
			total += card.getValue();
		}
		
		return true;
	}
	
	public boolean checkBust(){
		return (total > 21);
	}
	
	public boolean checkBlackjack(){
		return (total == 21);
	}
	
	//for testing
	public boolean setTotal(int total){
		this.total = total;
		return true;
	}
	
	public boolean setNumWins(int wins){
		numWins = wins;
		return true;
		
	}
	
	public boolean setHand(ArrayList<Card> newHand){
		hand = newHand;
		return true;
	}
	
}
