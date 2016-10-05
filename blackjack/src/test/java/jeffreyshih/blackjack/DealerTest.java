package jeffreyshih.blackjack;

import static org.junit.Assert.*;

import org.junit.Test;

public class DealerTest {

	@Test
	//return 2 if total < 17, return 0 if total >= 17
	public void turnTest(){
		Dealer dealer = new Dealer();
		Card ten = new Card("10", 10);
		Card two = new Card("2", 2);
		dealer.addToHand(ten);
		dealer.addToHand(two);
		dealer.updateTotal();	//total is 12 so hit
		
		assertEquals(2, dealer.turn());
		
		Card eight = new Card("8", 8);	
		dealer.addToHand(eight);
		dealer.updateTotal();	//total is 20, so no hit
		
		assertEquals(0, dealer.turn());
	}
}
