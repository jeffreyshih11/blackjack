package jeffreyshih.blackjack;

import static org.junit.Assert.*;

import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

public class PlayerTest {

	Scanner in = new Scanner(System.in);
	
	/*@Test
	public void inputNameTest() {
		
		String name;
		System.out.print("What is your name?  ");
		name = in.next( );
		
		Player player = new Player(name);
		System.out.println("Player name is: " + player.getName());
		assertEquals(name, player.getName());
		
	}*/
	
	@Test 
	public void createPlayerTest(){
		Player player = new Player("Test");
		assertEquals("Test", player.getName());
		assertEquals(0, player.getTotal());
		assertEquals(0, player.getNumWins());
		assertEquals(0, player.getHand().size());
	}
	
	@Test
	public void addToHandTest(){
		Player player = new Player("Test");
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
	public void updateTotalTest(){
		Player player = new Player("Test");
		Card two = new Card("2", 2);
		player.addToHand(two);
		assertEquals(0, player.getTotal());
		player.updateTotal();
		assertEquals(2, player.getTotal());
	}
	
	@Test
	public void checkBustTest(){
		Player player = new Player("Test");
		player.setTotal(1);
		assertFalse(player.checkBust());
		player.setTotal(21);
		assertFalse(player.checkBust());
		player.setTotal(22);
		assertTrue(player.checkBust());
	}
	
	@Test
	public void checkBlackjackTest(){
		Player player = new Player("Test");
		player.setTotal(1);
		assertFalse(player.checkBlackjack());
		player.setTotal(22);
		assertFalse(player.checkBlackjack());
		player.setTotal(21);
		assertTrue(player.checkBlackjack());
	}
	
	@Test
	public void hasAceTest(){
		Player player = new Player("Test");
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
	public void changeAceTest(){
		Player player = new Player("Test");
		Card ace11 = new Card("A", 11);
		Card ace1 = new Card("A", 1);
		
		player.addToHand(ace1);
		assertFalse(player.changeAce());
		
		player.addToHand(ace11);
		assertTrue(player.changeAce());
	}
	
	@Test
	public void processAceTest(){
		Player player = new Player("Test");
		Card ace11 = new Card("A", 11);
		Card two = new Card("2", 2);
		
		player.addToHand(ace11);
		player.addToHand(two);
		player.updateTotal();
		assertEquals(13, player.getTotal());
		
		player.processAce();
		assertEquals(3, player.getTotal());
		assertEquals(-1, player.hasAce());
		
		//reassurance that processing twice doesn't change anything
		player.processAce();
		assertEquals(3, player.getTotal());
		assertEquals(-1, player.hasAce());
	}
	
	@Test 
	public void addWinTest(){
		Player player = new Player("Test");
		
		assertEquals(0, player.getNumWins());
		
		player.addWin();
		assertEquals(1, player.getNumWins());
		player.addWin();
		assertEquals(2, player.getNumWins());
		
	}
	
	@Test
	public void getHandTest(){
		Player player = new Player("Test");
		Card ace11 = new Card("A", 11);
		Card two = new Card("2", 2);
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(ace11);
		cards.add(two);
		player.setHand(cards);
		assertEquals(cards, player.getHand());
	}
	
	@Test
	public void resetTest(){
		Player player = new Player("Test");
		Card ace11 = new Card("A", 11);
		Card two = new Card("2", 2);
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(ace11);
		cards.add(two);
		player.setHand(cards);
		player.addWin();
		
		assertEquals(2, player.getHand().size());
		assertEquals(13, player.getTotal());
		assertEquals(1, player.getNumWins());
		
		player.reset();
		assertEquals(0, player.getHand().size());
		assertEquals(0, player.getTotal());
		assertEquals(1, player.getNumWins());
	}

}
