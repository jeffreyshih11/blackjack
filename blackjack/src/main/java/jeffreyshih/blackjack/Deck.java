package jeffreyshih.blackjack;

import java.util.*;

public class Deck {

	//Will be a stack of 'Card' objects
	private Stack<Card> deck = new Stack<Card>();
	
	//populate deck with cards, numerical cards first then JQKA
	public Deck(){
		//for card 2-9
		for(int i = 2; i <= 10; i++){
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
	
	//shuffle the deck
	public boolean shuffle(){
		Collections.shuffle(deck);
		return true;
	}
	
	//pop the top card from the deck and return it
	//return null if empty stack exception happens
	public Card hit(){
		try{
			return (Card) deck.pop();
		}
		catch(Exception e){
			return null;
		}
	}
	
	//for testing purposes
	public Stack<Card> getDeck(){
		return deck;
	}
	
}
