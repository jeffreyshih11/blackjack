package jeffreyshih.blackjack;

import java.util.Scanner;

public class PlayGame {

	/*
	 * create dealer create deck and shuffle (just add cards back and shuffle if
	 * playing again) prompt player to enter name and then create human player
	 * ask if ready deal cards player first, then dealer dealer always has to
	 * hit if total < 17 end game if human busts end game if dealer value goes
	 * above human total or bust or tie ask to play again
	 * 
	 */

	static Player dealer;
	static Player human;
	static Deck deck;
	static boolean firstTime = true;
	static boolean gameOver = false;
	static boolean end = false;
	static Scanner in = new Scanner(System.in);

	public static void main(String args[]) {
		System.out.println("Let's play some blackjack!");
		setUp();
		System.out.println("Welcome " + human.getName() + "!");

		while (!end) {

			if (firstTime) {
				System.out.println("Ready to play? (y/n)");
				firstTime = false;
			} else {
				System.out.println("Wins for " + human.getName() + ": " + human.getNumWins());
				System.out.println("Wins for the dealer: " + dealer.getNumWins());
				System.out.println("Play again? (y/n)");
				reset();
			}

			String ready = in.next();
			if (ready.equals("y")) {

				play();
			} else if (ready.equals("n")) {
				end = true;
			} else {
				System.out.println("Please type 'y' or 'n'");
				//ready = in.next();
			}
		}
		System.out.println("Thanks for playing!");
	}

	public static boolean setUp() {

		if (firstTime) {
			dealer = new Player("Dealer");

			System.out.print("First, what is your name?  ");
			String name = in.next();

			human = new Player(name);
		}

		deck = new Deck();
		deck.shuffle();

		human.addToHand(deck.hit());
		human.addToHand(deck.hit());
		dealer.addToHand(deck.hit());
		dealer.addToHand(deck.hit());

		return true;
	}

	public static boolean reset() {
		dealer.reset();
		human.reset();
		setUp();
		return true;
	}

	public static boolean play() {
		// System.out.println("play game here");

		// human side
		boolean endTurn = false;
		boolean winByBlackjack = false;
		boolean loseByBust = false;

		while (!endTurn) {

			human.showHandAndTotal();

			// Check if player bust
			if (human.checkBust()) {
				if (human.hasAce() == -1) {
					System.out.println("~You bust!~");
					endTurn = true;
					//humanLoseByBust = true;
					dealer.addWin();
					return true;
				}
				else{
					//System.out.println("found ace==========");
					human.changeAce();
					human.updateTotal();
					System.out.println("Value of ace change from 11 to 1");
					//System.out.println("==========New total is :" + human.getTotal());
				}
			}
			// check if player got blackjack
			else if (human.checkBlackjack()) {
				System.out.println("~Blackjack! You win!~");
				endTurn = true;
				winByBlackjack = true;
				human.addWin();
			}
			// continue playing if neither
			else {
				System.out.println("Hit or Stay? (h/s)");
				String action = in.next();
				if (action.equals("h")) {
					human.addToHand(deck.hit());

				} else if (action.equals("s")) {
					endTurn = true;
				} else {
					System.out.println("Please type 'h' or 's'");
					//action = in.next();
				}
			}

		}

		//System.out.println("DONE-------------");
		
		
		// dealer
		endTurn = false;		
		
		if (winByBlackjack || loseByBust) {
			return true;
		} 
		else {
			System.out.println("-----Dealer's Turn------");
			while (!endTurn) {

				dealer.showHandAndTotal();

				// Check if dealer bust
				if (dealer.checkBust()) {
					if (dealer.hasAce() == -1) {
						System.out.println("Dealer bust!");
						endTurn = true;
						//dealerLoseByBust = true;
						human.addWin();
						return true;
					}
					else{
						//System.out.println("found ace==========");
						dealer.changeAce();
						dealer.updateTotal();
						System.out.println("Value of ace change from 11 to 1");
						//System.out.println("==========New total is :" + dealer.getTotal());
					}
				}
				// check if dealer got blackjack
				else if (dealer.checkBlackjack()) {
					System.out.println("Blackjack! Dealer won!");
					endTurn = true;
					//dealerWinByBlackjack = true;
					dealer.addWin();
					return true;
				}
				
				// continue playing if neither
				else {
					if(dealer.getTotal() < 17){
						System.out.println("Dealer will hit");
						dealer.addToHand(deck.hit());
					}
					else{
						System.out.println("Dealer stays");
						endTurn = true;
					}
				}

			}
		}
		
		if(human.getTotal() > dealer.getTotal()){
			human.addWin();
			System.out.println("~You beat the dealer!~");
		}
		else if(dealer.getTotal() > human.getTotal()){
			dealer.addWin();
			System.out.println("~The dealer won!~");
		}
		else if(human.getTotal() == dealer.getTotal()){
			System.out.println("--Tie Game--");
		}

		return true;
	}

	
}
