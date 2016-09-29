package jeffreyshih.blackjack;

public class Card {

	private String rank;
	private int value;
	
	public Card(String rank,int value){
		this.rank = rank;
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
	
	/*
	 * Only changing from 11 to 1 because value will
	 * always start as 11 and will only be changed if
	 * the total goes above 21 while the value is 11.
	 */
	
	public boolean changeAceValue(){
		if(rank.equals("A")){
			if(value == 11){
				value = 1;
				return true;
			}
		}
		
		return false;
	}
	
	public String getRank(){
		return rank;
	}
	
	//for testing purposes
	public String toString(){
		String out = "Rank: " + rank + "   Value: " + value;
		return out;
	}
}
