package jeffreyshih.blackjack;

import static org.junit.Assert.*;

import org.junit.Test;

public class HumanTest {

	@Test
	//return 2 if 'h' is passed (hit)
	//return 0 if 's' is passed (stay)
	//return -2 if neither of the above is passed
	public void turnTest(){
		Human human = new Human("Test");
		assertEquals(2, human.turn("h"));
		assertEquals(0, human.turn("s"));
		assertEquals(-2, human.turn(""));
		
	}
}
