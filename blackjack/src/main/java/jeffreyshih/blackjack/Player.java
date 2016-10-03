package jeffreyshih.blackjack;

import java.util.ArrayList;

public class Player {

	private String name;
	private ArrayList<Card> hand;
	private int total;
	private int numWins;

	public Player(String name) {
		this.name = name;
		hand = new ArrayList<Card>();
		total = 0;
		numWins = 0;
	}

	public boolean reset() {
		hand.clear();
		total = 0;
		return true;
	}

	public boolean addToHand(Card card) {
		return hand.add(card);
	}

	public boolean updateTotal() {
		total = 0;
		for (Card card : hand) {
			total += card.getValue();
		}

		return true;
	}

	public boolean checkBust() {
		return (total > 21);
	}

	public boolean checkBlackjack() {
		return (total == 21);
	}

	public int hasAce() {
		//System.out.println("looking for ace");
		for(Card card: hand){
			if(card.getValue() == 11){
				return hand.indexOf(card);
			}
		}
		return -1;
	}

	// return true if there is an ace and its value got changed
	public boolean changeAce() {
		int aceIdx = hasAce();
		if(aceIdx != -1){	//redundant check but for safety 
			hand.get(aceIdx).changeAceValue();
			return true;
		}
		return false;

	}

	//
	public void processAce(){
		changeAce();
		updateTotal();
		System.out.println("---Value of ace change from 11 to 1---");
		System.out.println("Updated Total:");
		showHandAndTotal();
		// dealer.getTotal());
	}
	
	public boolean showHandAndTotal() {
		// check hand a total
		System.out.print("	Hand: ");
		String sHand = "";
		for (Card card : getHand()) {
			sHand += card.getRank() + " ";
		}

		// print hand and total
		System.out.println(sHand);
		updateTotal();
		System.out.println("	Total: " + getTotal());

		return true;
	}

	public boolean addWin() {
		numWins++;
		return true;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public int getTotal() {
		return total;
	}

	public int getNumWins() {
		return numWins;
	}
	
	
	// for testing purposes
	
	public boolean setTotal(int total) {
		this.total = total;
		return true;
	}

	public boolean setNumWins(int wins) {
		numWins = wins;
		return true;

	}

	public boolean setHand(ArrayList<Card> newHand) {
		hand = newHand;
		updateTotal();
		return true;
	}

}
