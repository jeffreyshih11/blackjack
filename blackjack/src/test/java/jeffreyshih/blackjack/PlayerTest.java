package jeffreyshih.blackjack;

import static org.junit.Assert.*;

import java.io.Console;
import java.util.Scanner;

import org.junit.Test;

public class PlayerTest {

	Scanner in = new Scanner(System.in);
	
	@Test
	public void inputNameTest() {
		
		String name;
		System.out.print("What is your name?  ");
		name = in.next( );
		
		Player person = new Player(name);
		System.out.println("Player name is: " + person.getName());
		assertEquals(name, person.getName());
		
	}

}
