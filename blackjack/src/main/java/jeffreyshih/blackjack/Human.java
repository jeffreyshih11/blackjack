package jeffreyshih.blackjack;

import java.util.Scanner;

public class Human extends Player {

	Scanner in = new Scanner(System.in);
	
	public Human(String name) {
		super(name);
	}

	// Return 0 if player stays, 2 if hit, -2 if player mistypes(continue playing)
	public int turn() {
		System.out.println("Hit or Stay? (h/s)");
		String action = in.next();
		if (action.equals("h")) {
			return 2;
		} else if (action.equals("s")) {
			return 0;
		} else {
			return -2;
		}

	}
}
