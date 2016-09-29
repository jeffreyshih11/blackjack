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

	static boolean endTurn;

	// for human
	static boolean winByBlackjack = false;
	static boolean loseByBust = false;

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
				// ready = in.next();
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

		winByBlackjack = false;
		loseByBust = false;

		return true;
	}

	public static boolean reset() {
		dealer.reset();
		human.reset();
		setUp();
		return true;
	}

	public static boolean play() {
		// human side
		endTurn = false;

		System.out.println("-----Your turn-----");
		while (!endTurn) {
			if (humanTurn()) {
				return true;
			}
		}

		// dealer
		endTurn = false;

		System.out.println("-----Dealer's Turn------");
		while (!endTurn) {
			if (dealerTurn()) {
				return true;
			}
		}

		System.out.println("");
		getWinner();

		return true;
	}

	private static void getWinner() {
		if (human.getTotal() > dealer.getTotal()) {
			human.addWin();
			System.out.println("~You beat the dealer!~\n");
		} else if (dealer.getTotal() > human.getTotal()) {
			dealer.addWin();
			System.out.println("~The dealer won!~\n");
		} else if (human.getTotal() == dealer.getTotal()) {
			System.out.println("--Tie Game--\n");
		}
	}

	private static boolean humanTurn() {
		human.showHandAndTotal();

		// Check if player bust
		if (checkBustOrBlackjack(human)){
			return true;
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
				// action = in.next();
			}
		}

		return false;
	}

	private static boolean dealerTurn() {
		dealer.showHandAndTotal();

		// Check if dealer bust
		if (checkBustOrBlackjack(dealer)){
			return true;
		}

		// continue playing if neither
		else {
			if (dealer.getTotal() < 17) {
				System.out.println("Dealer will hit");
				dealer.addToHand(deck.hit());
			} else {
				System.out.println("Dealer stays");
				endTurn = true;
			}
		}

		return false;
	}

	private static boolean checkBustOrBlackjack(Player player) {
		// Check if dealer bust
		if (player.checkBust()) {
			if (processBust(player)) {
				return true;
			}
		}

		// check if dealer got blackjack
		else if (player.checkBlackjack()) {

			processBlackjack(player);
			return true;
		}
		
		return false;
	}

	private static boolean processBust(Player player) {
		if (player.hasAce() == -1) {
			if (player.getName().equals("Dealer")) {
				System.out.println("Dealer bust!\n");
				human.addWin();
			} else {
				System.out.println("~You bust!~\n");
				dealer.addWin(); // dealer wins if you bust no matter what
			}

			endTurn = true;
			return true;
		} else {
			// System.out.println("found ace==========");
			player.changeAce();
			player.updateTotal();
			System.out.println("Value of ace change from 11 to 1");
			// System.out.println("==========New total is :" +
			// dealer.getTotal());
			return false;
		}
	}

	private static boolean processBlackjack(Player player) {
		endTurn = true;
		if (player.getName().equals("Dealer")) {
			System.out.println("Blackjack!");
			endTurn = true;
			// dealerWinByBlackjack = true;
			// dealer.addWin();
			if (winByBlackjack) {
				if (human.getHand().size() == 2) {
					if (dealer.getHand().size() == 2) {
						System.out.println("--Tie Game--\n");
					} else {
						System.out.println("~You won!~");
					}
				} else {
					if (dealer.getHand().size() == 2) {
						System.out.println("~~Dealer won!~~\n");
					}
				}
				System.out.println("--Tie Game--\n");
			} else {
				System.out.println("~~Dealer won!~~\n");
				dealer.addWin();
			}
			return true;
		} else {
			System.out.println("~Blackjack! But let's give the dealer a chance...~\n");
			winByBlackjack = true;
			return true;
		}
	}

}
