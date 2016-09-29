package jeffreyshih.blackjack;

import static org.junit.Assert.*;

import org.junit.Test;

public class CardTest {

	
	@Test
	public void changeAceTest() {
		Card ace = new Card("A", 11);	//default for Aces when they get put in the deck
		assertEquals(ace.getValue(), 11);
		
		ace.changeAceValue();
		assertEquals(ace.getValue(), 1);
		
		ace.changeAceValue();	//test that changeAceValue only works when ace value is 11
		assertFalse(ace.getValue() == 11);
		
		Card two = new Card("2", 2);	//create other card to make sure changeAceValue only works with aces
		two.changeAceValue();
		assertTrue(two.getValue() != 1);
	}

}
