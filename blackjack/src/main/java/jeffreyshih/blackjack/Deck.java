package jeffreyshih.blackjack;

import java.util.*;

public class Deck {

	//Will be a stack of 'Card' objects
	private Stack deck = new Stack();
	
	public Deck(){
		//for card 2-9
		for(int i = 1; i < 10; i++){
			for(int j = 0; j < 4; j++){
				String rank = String.valueOf(i);
				Card toAdd = new Card(rank, i);
				
				deck.add(toAdd);
			}
		}
		
		//for jack, queen, king, ace
		int JQKVal = 10;
		int aceVal = 11;
		for(int k = 0; k < 4; k++){
			Card jack = new Card("J", JQKVal);
			Card queen = new Card("Q", JQKVal);
			Card king = new Card("K", JQKVal);
			Card ace = new Card("A", aceVal);
			
			deck.add(jack);
			deck.add(queen);
			deck.add(king);
			deck.add(ace);
		}
	}
	
	public boolean shuffle(){
		//shuffle the deck
		Collections.shuffle(deck);
		return true;
	}
	
	public Card hit(){
		//pop the top card from the deck and return it
		return (Card) deck.pop();
	}
	
	//for testing purposes
	public Stack getDeck(){
		return deck;
	}
	
}
