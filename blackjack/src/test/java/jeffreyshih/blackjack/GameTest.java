package jeffreyshih.blackjack;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.junit.Test;

public class GameTest {

	Game game = new Game();
	Player player = new Player("Tester");
	Player dealer = new Player("Dealer");
	ArrayList<Card> playerHand = new ArrayList<Card>();
	ArrayList<Card> dealerHand = new ArrayList<Card>();
	
	@Test
	public void humanWin() {
		System.out.println("Human win testing....");
		playerHand.add(new Card("9", 9));
		playerHand.add(new Card("9", 9));
		player.setHand(playerHand);
		
		dealerHand.add(new Card("8", 8));
		dealerHand.add(new Card("8", 8));
		dealer.setHand(dealerHand);
		
		assertEquals(1, game.getWinner(player, dealer));
		//fail("Not yet implemented");
	}

	@Test
	public void dealerWin() {
		System.out.println("Dealer win testing....");
		dealerHand.add(new Card("9", 9));
		dealerHand.add(new Card("9", 9));
		dealer.setHand(dealerHand);
		
		playerHand.add(new Card("8", 8));
		playerHand.add(new Card("8", 8));
		player.setHand(playerHand);
		
		assertEquals(0, game.getWinner(player, dealer));
	}
	
	@Test
	public void humanBlackjackWin() {
		fail("Not yet implemented");
	}
	
	@Test
	public void humanBust() {
		fail("Not yet implemented");
	}
	
	@Test
	public void dealerBlackjackWin() {
		fail("Not yet implemented");
	}
	
	@Test
	public void dealerBust() {
		fail("Not yet implemented");
	}
	
	@Test
	public void tie() {
		fail("Not yet implemented");
	}

	@Test
	public void blackjackTie() {
		fail("Not yet implemented");
	}
}

