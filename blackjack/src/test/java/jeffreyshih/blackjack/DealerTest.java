package jeffreyshih.blackjack;

import static org.junit.Assert.*;

import org.junit.Test;

public class DealerTest {

	@Test
	public void turnTest(){
		Dealer dealer = new Dealer();
		Card ten = new Card("10", 11);
		Card two = new Card("2", 2);
		dealer.addToHand(ten);
		dealer.addToHand(two);
		dealer.updateTotal();
		
		assertEquals(2, dealer.turn());
		
		Card eight = new Card("8", 8);
		dealer.addToHand(eight);
		dealer.updateTotal();
		
		assertEquals(0, dealer.turn());
	}
}
