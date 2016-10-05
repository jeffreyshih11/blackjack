package jeffreyshih.blackjack;

import java.util.Scanner;

public class Game {

	/*
	 * create dealer create deck and shuffle (get full deck and shuffle if
	 * playing again) prompt player to enter name and then create human player
	 * ask if ready deal cards player first, then dealer dealer always has to
	 * hit if total < 17 end game right away if human busts end game, if human
	 * gets blackjack, give the dealer a chance. if dealer gets blackjack and
	 * human didn't, end game, if dealer busts end game. if neither bust or get
	 * blackjack, compare totals and determine winner. Ask to play again until
	 * user says no.
	 * 
	 */

	Dealer dealer;
	Human human;
	Deck deck;
	Scanner in = new Scanner(System.in);

	boolean firstTime = true;
	// boolean endTurn;

	// for human
	boolean winByBlackjack = false;
	boolean loseByBust = false;

	/*
	 * Main method to run the game.
	 * Keeps playing until user enters 'n' when prompted to play again or if they are ready
	 */
	public void start() {

		System.out.println("Let's play some blackjack!");
		setUp();
		System.out.println("Welcome " + human.getName() + "!");
		
		boolean end = false;
		while (!end) {

			if (firstTime) {
				System.out.println("Ready to play? (y/n)");
				firstTime = false;
			} else {
				System.out.println("--------------------");
				System.out.println("Wins for " + human.getName() + ": " + human.getNumWins());
				System.out.println("Wins for the dealer: " + dealer.getNumWins());
				System.out.println("--------------------");
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
			}
		}
		System.out.println("Thanks for playing!");
	}

	/*
	 * Creates the deck, shuffles it, then deals the players
	 * their cards. If it's the first game, user will be prompted
	 * to enter their name and the player and dealer will be created
	 * 
	 */
	public boolean setUp() {

		if (firstTime) {
			dealer = new Dealer();

			System.out.print("First, what is your name?  ");
			String name = in.next();

			human = new Human(name);
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

	/*
	 * Resets the dealer and human and sets up a new game
	 */
	public boolean reset() {
		dealer.reset();
		human.reset();
		setUp();
		return true;
	}

	/*
	 * Plays the game, human goes first.
	 * If human or dealer busts, return immediately 
	 * Turn ends when player stay or blackjacks
	 * Compare totals after both turns end
	 */
	public int play() {
		// human side
		boolean endTurn = false;

		System.out.println("-----Your turn-----");
		while (!endTurn) {
			int action = turn(human);
			// bust
			if (action == -1) {
				dealer.addWin(); // dealer wins if you bust no matter what
				return -1;
			}
			// hit
			else if (action == 2) {
				human.addToHand(deck.hit());
			}
			// mistype
			else if (action == -2) {
				System.out.println("Please type 'h' or 's'");
			}
			// blackjack in 2 cards, stay, blackjack not in 2 cards
			else if (action == 1 || action == 0 || action == 3) {
				endTurn = true;
			}
		}

		// dealer
		endTurn = false;
		System.out.println("-----Dealer's Turn------");

		while (!endTurn) {
			int action = turn(dealer);
			// bust
			if (action == -1) {
				human.addWin();
				return 1;
			}
			// bust
			else if (action == 2) {
				dealer.addToHand(deck.hit());
			}
			// blackjack and end, stay
			else if (action == 1 || action == 0) {
				endTurn = true;
			}
		}

		System.out.println("");
		

		return getWinner(human, dealer);
	}

	/*
	 * Return 1 if human wins 0 if dealer wins -1 if draw -2 if error (should
	 * never return -2)
	 */
	public int getWinner(Player person, Player Dealer) {
		if (person.getTotal() > Dealer.getTotal()) {
			person.addWin();
			System.out.println("~~You beat the dealer!~~\n");
			return 1;
		} else if (Dealer.getTotal() > person.getTotal()) {
			Dealer.addWin();
			System.out.println("The dealer won :(\n");
			return 0;

		} else if (person.getTotal() == Dealer.getTotal()) {
			System.out.println("--Tie Game--\n");
			return -1;

		}
		return -2;
	}

	// return 1 if blackjack, -1 if bust, 0 if player stays, 2 if hit, -2
	// if mistype from player (continue playing), 3 for
	// human blackjack and continue
	public int turn(Player player) {
		player.showHandAndTotal();

		int result = checkBustOrBlackjack(player);
		if (result != 0 && result != 3) {
			return result;
		} else if (result == 3) {
			return result;
		} else {
			if (player.getName().equals("Dealer")) {
				return dealer.turn();
			} else {
				System.out.println("Hit or Stay? (h/s)");
				String action = in.next();
				return human.turn(action);
			}

		}
	}

	/*
	 * Return 1 for blackjack and win, -1 if bust, 3 for blackjack and continue,
	 * 0 if neither
	 */
	public int checkBustOrBlackjack(Player player) {
		// Check if bust
		/*
		 * if (player.checkBust()) { return processBust(player); }
		 */

		int bust = processBust(player);
		if (bust != 0) {
			return bust;
		}

		return processBlackjack(player);

		/*
		 * int processBJ = processBlackjack(player); if (processBJ != 0) {
		 * return processBJ; }
		 * 
		 * return 0;
		 */
	}

	/*
	 * Return -1 is the player bust, 0 if not
	 */
	public int processBust(Player player) {
		// no ace, so bust
		if (player.checkBust()) {
			if (player.hasAce() == -1) {
				if (player.getName().equals("Dealer")) {
					System.out.println("Dealer bust!\n");

				} else {
					System.out.println("~You bust!~\n");

				}

				return -1;
			}
			// has ace so no bust
			else {
				player.processAce();
				return processBust(player); // check if bust even after
											// transforming ace

			}
		}
		return 0;
	}

	/*
	 *  return 1 to end the game, 3 for blackjack but keep going, 0 for no blackjack
	 */
	public int processBlackjack(Player player) {
		// endTurn = true;
		if (player.checkBlackjack()) {
			// dealer got blackjack
			if (player.getName().equals("Dealer")) {
				// processBJDealer();
				return 1;
			}
			// if human got blackjack
			else {
				System.out.println("~Blackjack! But let's give the dealer a chance...~\n");
				winByBlackjack = true;
				return 3;
			}
		} else {
			// you got blackjack in 2 but dealer didnt
			if (winByBlackjack) {
				if (human.getHand().size() == 2) {
					return 1;
				}
			}

			// keep going because neither players have blackjack
			return 0;
		}

	}

	// prints out the correct message (turned to be redundant)
	/*
	public void processBJDealer() {
		System.out.println("Blackjack!");

		// check if the human also got blackjack
		if (winByBlackjack) {
			if (human.getHand().size() == 2) { // both players got blackjack in 2 cards
				if (dealer.getHand().size() == 2) {
					System.out.println("--Tie Game--\n");
				}
			}
		} else { // dealer got blackjack in 2 but you didnt
			if (dealer.getHand().size() == 2) {
				System.out.println("~~Dealer won!~~\n");
			} // tie game if both get blackjack but not in 2 cards
			else {
				System.out.println("--Tie Game--\n");
			}
		}
	}
	*/

}
