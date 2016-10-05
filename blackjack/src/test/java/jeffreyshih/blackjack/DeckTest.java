package jeffreyshih.blackjack;

import static org.junit.Assert.*;

import org.junit.Test;

public class DeckTest {
	Deck deck = new Deck();

	@Test
	// Print out deck after init, should be in AKQJ x4 and then 2-9 x4 for each
	// number (I find it easier to see it than create asserts)
	public void initTest() {

		System.out.println("Check deck");
		while (!deck.getDeck().isEmpty()) {
			Card card = deck.hit();
			System.out.println(card.toString());
		}

		System.out.println("-----------------------------------------");
		assertTrue(true);

	}

	@Test
	// print deck out to show that it did get shuffled (I find it easier to see
	// it than create asserts)
	public void shuffleTest() {

		System.out.println("Check if deck got shuffled");
		deck.shuffle();

		while (!deck.getDeck().isEmpty()) {
			Card card = deck.hit();
			System.out.println(card.toString());
		}
		System.out.println("-----------------------------------------");
		assertTrue(true);
	}

	@Test
	// make sure the card the top of the stack is returned and removed from the
	// deck
	public void hitTest() {

		deck.shuffle();
		Card topOfDeck = deck.getDeck().peek();
		Card popped = deck.hit();
		assertEquals(topOfDeck, popped);

		assertEquals(51, deck.getDeck().size());

		for (int i = 0; i < 51; i++) {
			deck.hit();
		}

		// test to make sure null is returned is deck is empty
		assertEquals(0, deck.getDeck().size());
		assertNull(deck.hit());
	}
}
