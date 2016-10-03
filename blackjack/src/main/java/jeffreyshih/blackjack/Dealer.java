package jeffreyshih.blackjack;

public class Dealer extends Player {

	public Dealer() {
		super("Dealer");
	}

	// Return 0 if dealer stays, 2 if hit to follow convention in turn()
	public int turn() {
		if (getTotal() < 17) {
			System.out.println("Dealer will hit");
			return 2;
		} else {
			System.out.println("Dealer stays");
			return 0;
		}
	}
	
	
}
