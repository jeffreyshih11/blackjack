package jeffreyshih.blackjack;

import static org.junit.Assert.*;

import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

public class PlayerTest {

	Scanner in = new Scanner(System.in);
	Player player = new Player("Test");

	/*
	 * @Test public void inputNameTest() {
	 * 
	 * String name; System.out.print("What is your name?  "); name = in.next( );
	 * 
	 * Player player = new Player(name); System.out.println("Player name is: " +
	 * player.getName()); assertEquals(name, player.getName());
	 * 
	 * }
	 */

	@Test
	// make sure the player is created correctly
	public void createPlayerTest() {

		assertEquals("Test", player.getName());
		assertEquals(0, player.getTotal());
		assertEquals(0, player.getNumWins());
		assertEquals(0, player.getHand().size());
	}

	@Test
	//make sure the card gets added to players hand
	public void addToHandTest() {

		Card ace = new Card("A", 11);
		player.addToHand(ace);
		assertEquals(1, player.getHand().size());
		assertEquals(ace, player.getHand().get(0));

		Card two = new Card("2", 2);
		player.addToHand(two);
		assertEquals(2, player.getHand().size());
		assertEquals(two, player.getHand().get(1));
	}

	@Test
	//total should only be changed when updateTotal() is called
	public void updateTotalTest() {

		Card two = new Card("2", 2);
		player.addToHand(two);
		assertEquals(0, player.getTotal());
		player.updateTotal();
		assertEquals(2, player.getTotal());
	}

	@Test
	//only return true if total > 21
	public void checkBustTest() {

		player.setTotal(1);
		assertFalse(player.checkBust());
		player.setTotal(21);
		assertFalse(player.checkBust());
		player.setTotal(22);
		assertTrue(player.checkBust());
	}

	@Test
	//make sure it only returns true when total == 21
	public void checkBlackjackTest() {

		player.setTotal(1);
		assertFalse(player.checkBlackjack());
		player.setTotal(22);
		assertFalse(player.checkBlackjack());
		player.setTotal(21);
		assertTrue(player.checkBlackjack());
	}

	@Test
	//Should only return true if there is an ace with value 11
	public void hasAceTest() {

		Card ace11 = new Card("A", 11);
		Card two = new Card("2", 2);
		Card ace1 = new Card("A", 1);

		player.addToHand(two);
		assertEquals(-1, player.hasAce());

		player.addToHand(ace1);
		assertEquals(-1, player.hasAce());

		player.addToHand(ace11);
		assertEquals(2, player.hasAce());

	}

	@Test
	//should only be true if it changes an ace with value 11
	public void changeAceTest() {

		Card ace11 = new Card("A", 11);
		Card ace1 = new Card("A", 1);

		player.addToHand(ace1);
		//shouldn't be changing an ace with value 1
		assertFalse(player.changeAce());

		player.addToHand(ace11);
		//change the ace whose value is 11
		assertTrue(player.changeAce());
	}

	@Test
	public void processAceTest() {

		Card ace11 = new Card("A", 11);
		Card two = new Card("2", 2);

		player.addToHand(ace11);
		player.addToHand(two);

		// just to make sure the value of ac11 is 11 and then total is 13
		player.updateTotal();
		assertEquals(13, player.getTotal());

		// check the total is updated and there is no longer an ace with 11 as
		// its value
		player.processAce();
		assertEquals(3, player.getTotal());
		assertEquals(-1, player.hasAce());

		// reassurance that processing twice doesn't change anything
		player.processAce();
		assertEquals(3, player.getTotal());
		assertEquals(-1, player.hasAce());
	}

	@Test
	public void addWinTest() {
		// make sure numWins is correct after calling addWin()
		assertEquals(0, player.getNumWins());
		player.addWin();
		assertEquals(1, player.getNumWins());
		player.addWin();
		assertEquals(2, player.getNumWins());

	}

	@Test
	public void getHandTest() {

		Card ace11 = new Card("A", 11);
		Card two = new Card("2", 2);
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(ace11);
		cards.add(two);
		player.setHand(cards);
		assertEquals(cards, player.getHand());
	}

	@Test
	//reset should set players hand to an empty array and the total to 0
	//but should leave the numWins unchanged
	public void resetTest() {

		Card ace11 = new Card("A", 11);
		Card two = new Card("2", 2);
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(ace11);
		cards.add(two);
		player.setHand(cards);
		player.addWin();

		// make sure the values aren't 0
		assertEquals(2, player.getHand().size());
		assertEquals(13, player.getTotal());
		assertEquals(1, player.getNumWins());

		// reset all values to 0 except numWins
		player.reset();
		assertEquals(0, player.getHand().size());
		assertEquals(0, player.getTotal());
		assertEquals(1, player.getNumWins());
	}

}
