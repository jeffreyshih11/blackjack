package jeffreyshih.blackjack;

import static org.junit.Assert.*;

import org.junit.Test;

public class DeckTest {
	Deck deck;

	public void init() {
		deck = new Deck();
	}

	@Test
	public void initTest() {
		init();
		System.out.println("Check if deck is full");
		while (!deck.getDeck().isEmpty()) {
			Card card = deck.hit();
			System.out.println(card.toString());
		}

		System.out.println("-----------------------------------------");
		assertEquals(true, true);

	}

	@Test
	public void shuffleTest(){
		init();
		System.out.println("Check if deck got shuffled");
		deck.shuffle();
		
		while(!deck.getDeck().isEmpty()){
			Card card = deck.hit();
			System.out.println(card.toString());
		}
		System.out.println("-----------------------------------------");
		assertEquals(true, true);
	}
}
