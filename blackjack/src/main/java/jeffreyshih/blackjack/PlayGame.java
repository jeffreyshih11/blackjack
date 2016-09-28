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
			System.out.println("Ready to play? (y/n)");
			String ready = in.next();
			if (ready.equals("y")) {
				play();
			} else if (ready.equals("n")) {
				end = true;
			} else{
				System.out.println("Please type 'y' or 'n'");
				ready = in.next();
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
		} else {
			reset();
		}

		deck = new Deck();
		deck.shuffle();
		firstTime = false;
		return true;
	}

	public static boolean reset() {
		dealer.reset();
		human.reset();
		return true;
	}
	
	public static boolean play(){
		System.out.println("play game here");
		
		
		return true;
	}
}
