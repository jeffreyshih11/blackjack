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
	
	@Test public void getValueTest(){
		Card jack = new Card("J", 10);	
		assertEquals(jack.getValue(), 10);
		
		Card queen = new Card("Q", 10);	
		assertEquals(queen.getValue(), 10);
		
		Card king = new Card("K", 10);	
		assertEquals(king.getValue(), 10);
		
		Card two = new Card("2", 2);	
		assertEquals(two.getValue(), 2);
	
	}

	@Test public void getRankTest(){
		Card jack = new Card("J", 10);	
		assertEquals(jack.getRank(), "J");
		
		Card queen = new Card("Q", 10);	
		assertEquals(queen.getRank(), "Q");
		
		Card king = new Card("K", 10);	
		assertEquals(king.getRank(), "K");
		
		Card two = new Card("2", 2);	
		assertEquals(two.getRank(), "2");
	
	}
}
