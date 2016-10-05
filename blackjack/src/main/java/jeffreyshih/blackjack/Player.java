package jeffreyshih.blackjack;

import java.util.ArrayList;

/*
 * Player needs - name, list of cards in their hand
 * the sum of their cards
 * *added number of wins for continuous play*
 */
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

	/*
	 * Clear hand and set total to 0
	 */
	public boolean reset() {
		hand.clear();
		total = 0;
		return true;
	}

	/*
	 * add new card to players hand
	 */
	public boolean addToHand(Card card) {
		return hand.add(card);
	}

	/*
	 * get the new total of the cards in a players hand
	 */
	public boolean updateTotal() {
		total = 0;
		for (Card card : hand) {
			total += card.getValue();
		}

		return true;
	}

	/*
	 * see if the players total is greater than 21
	 */
	public boolean checkBust() {
		return (total > 21);
	}

	/*
	 * see if players total is 21
	 */
	public boolean checkBlackjack() {
		return (total == 21);
	}

	/*
	 * see if the player has an ace in their hand 
	 * return the index of the ace if it exists
	 * -1 otherwise
	 */
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

	//change the value of the ace and update the total
	public void processAce(){
		changeAce();
		updateTotal();
		System.out.println("---Value of ace change from 11 to 1---");
		System.out.println("Updated Total:");
		showHandAndTotal();
		// dealer.getTotal());
	}
	
	/*
	 * Prints the cards in the players hand
	 * and updates the total and shows the player
	 */
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

	//adds a win 
	public boolean addWin() {
		numWins++;
		return true;
	}

	//returns the players name
	public String getName() {
		return name;
	}

	//returns the players hand
	public ArrayList<Card> getHand() {
		return hand;
	}

	//returns the sum of the values of the cards in the players hand
	public int getTotal() {
		return total;
	}

	//returns the number of wins
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
