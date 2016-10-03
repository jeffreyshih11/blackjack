package jeffreyshih.blackjack;

import static org.junit.Assert.*;

import org.junit.Test;

public class DeckTest {
	Deck deck;

	public void init() {
		deck = new Deck();
	}

	@Test
	//Print out deck after init, should be in AKQJ x4 and then 2-9 x4 for each number
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
	//print deck out to show that it did get shuffled
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
	
	@Test 
	public void hitTest(){
		init();
		deck.shuffle();
		Card topOfDeck = deck.getDeck().peek();
		Card popped = deck.hit();
		assertEquals(topOfDeck, popped);
		
		assertEquals(51, deck.getDeck().size());
		
		for(int i = 0; i < 51; i++){
			deck.hit();
		}
		assertEquals(0, deck.getDeck().size());
		assertNull(deck.hit());
	}
}
