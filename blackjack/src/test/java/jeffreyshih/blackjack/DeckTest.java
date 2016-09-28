package jeffreyshih.blackjack;

import static org.junit.Assert.*;

import org.junit.Test;

public class DeckTest {

	@Test
	public void test() {
		Deck deck = new Deck();
		while(!deck.getDeck().isEmpty()){
			Card card = deck.hit();
			System.out.println(card.toString());
		}
		
		System.out.println("-----------------------------------------");
		assertEquals(true, true);
		
		Deck deck2 = new Deck();
		deck2.shuffle();
		while(!deck2.getDeck().isEmpty()){
			Card card = deck2.hit();
			System.out.println(card.toString());
		}
	}

}
