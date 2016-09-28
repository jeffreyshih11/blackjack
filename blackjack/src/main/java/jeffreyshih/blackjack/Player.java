package jeffreyshih.blackjack;

import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;

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
	
	public boolean reset(){
		hand.clear();
		total = 0;
		return true;
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
	
	//for testing purposes
	public String getName(){
		return name;
	}
	
	public ArrayList<Card> getHand(){
		return hand;
	}
	
	public int getTotal(){
		return total;
	}
	
	public int getNumWins(){
		return numWins;
	}
	
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
