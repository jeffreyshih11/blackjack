package jeffreyshih.blackjack;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.junit.Test;

public class GameTest {

	Game game = new Game();
	Human human = new Human("Tester");
	Dealer dealer = new Dealer();
	ArrayList<Card> humanHand = new ArrayList<Card>();
	ArrayList<Card> dealerHand = new ArrayList<Card>();

	@Test
	public void humanWin() {
		System.out.println("Human win testing....");
		humanHand.add(new Card("9", 9));
		humanHand.add(new Card("9", 9));
		human.setHand(humanHand);

		dealerHand.add(new Card("8", 8));
		dealerHand.add(new Card("8", 8));
		dealer.setHand(dealerHand);

		assertEquals(1, game.getWinner(human, dealer));
		// fail("Not yet implemented");
	}

	@Test
	public void dealerWin() {
		System.out.println("Dealer win testing....");
		dealerHand.add(new Card("9", 9));
		dealerHand.add(new Card("9", 9));
		dealer.setHand(dealerHand);

		humanHand.add(new Card("8", 8));
		humanHand.add(new Card("8", 8));
		human.setHand(humanHand);

		assertEquals(0, game.getWinner(human, dealer));
	}

	@Test
	public void tie() {
		humanHand.add(new Card("9", 9));
		human.setHand(humanHand);

		dealerHand.add(new Card("9", 9));
		dealer.setHand(dealerHand);

		assertEquals(-1, game.getWinner(human, dealer));
	}

	@Test
	public void processBlackjackTest() {
		// no blackjack
		humanHand.add(new Card("8", 8));
		humanHand.add(new Card("8", 8));
		human.setHand(humanHand);
		assertEquals(0, game.processBlackjack(human));

		// blackjack dealer
		dealerHand.add(new Card("A", 11));
		dealerHand.add(new Card("K", 10));
		dealer.setHand(dealerHand);
		game.human = human;
		game.dealer = dealer;
		assertEquals(1, game.processBlackjack(dealer));

		// blackjack human but not in 2 cards
		humanHand.add(new Card("5", 5));
		human.setHand(humanHand);
		assertEquals(3, game.processBlackjack(human));

		// blackjack human in 2 cards
		humanHand.clear();
		humanHand.add(new Card("K", 10));
		humanHand.add(new Card("A", 11));
		human.setHand(humanHand);
		assertEquals(3, game.processBlackjack(human));

		// blackjack human in 2 cards but no blackjack for dealer in 2 cards
		dealerHand.clear();
		dealerHand.add(new Card("K", 10));
		dealerHand.add(new Card("K", 10));
		dealer.setHand(dealerHand);
		game.human = human;
		assertEquals(1, game.processBlackjack(dealer));
	}

	@Test
	public void blackjackWinsTest() {
		// human wins with 2 cards
		humanHand.add(new Card("K", 10));
		humanHand.add(new Card("A", 11));
		human.setHand(humanHand);

		dealerHand.add(new Card("K", 10));
		dealerHand.add(new Card("K", 10));
		dealer.setHand(dealerHand);

		assertEquals(1, game.getWinner(human, dealer));

		// dealer wins with 2 cards
		humanHand.clear();
		humanHand.add(new Card("K", 10));
		humanHand.add(new Card("K", 10));
		human.setHand(humanHand);

		dealerHand.clear();
		dealerHand.add(new Card("K", 10));
		dealerHand.add(new Card("A", 11));
		dealer.setHand(dealerHand);

		assertEquals(0, game.getWinner(human, dealer));
		
		//both got blackjack in 2
		humanHand.clear();
		humanHand.add(new Card("K", 10));
		humanHand.add(new Card("A", 11));
		human.setHand(humanHand);
		
		dealerHand.clear();
		dealerHand.add(new Card("K", 10));
		dealerHand.add(new Card("A", 11));
		dealer.setHand(dealerHand);
		
		assertEquals(-1, game.getWinner(human, dealer));
		
		//both got blackjack in more than 2
		humanHand.clear();
		humanHand.add(new Card("K", 10));
		humanHand.add(new Card("9", 9));
		humanHand.add(new Card("2", 2));
		human.setHand(humanHand);
		
		dealerHand.clear();
		dealerHand.add(new Card("K", 10));
		dealerHand.add(new Card("9", 9));
		dealerHand.add(new Card("2", 2));
		dealer.setHand(dealerHand);
		
		assertEquals(-1, game.getWinner(human, dealer));

	}

	@Test
	public void processBustTest() {
		// no ace human bust
		humanHand.add(new Card("9", 9));
		humanHand.add(new Card("9", 9));
		humanHand.add(new Card("9", 9));
		human.setHand(humanHand);
		assertEquals(-1, game.processBust(human));

		// no ace dealer bust
		dealerHand.add(new Card("8", 8));
		dealerHand.add(new Card("8", 8));
		dealerHand.add(new Card("8", 8));
		dealer.setHand(dealerHand);
		assertEquals(-1, game.processBust(dealer));

		// ace bust
		human.addToHand(new Card("A", 11));
		assertEquals(-1, game.processBust(human));

		// no bust
		humanHand.clear();
		humanHand.add(new Card("9", 9));
		humanHand.add(new Card("10", 10));
		human.setHand(humanHand);
		assertEquals(0, game.processBust(human));

		// no bust with ace
		human.addToHand(new Card("A", 11));
		assertEquals(0, game.processBust(human));
	}
}
