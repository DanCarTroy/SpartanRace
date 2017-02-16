package spartanRace;

public class Card {
	
	private int cardNum;
	private int cardValue;
	
	/**
	 * Constructor. Sets the card number and its value. 
	 * @param cardNum the number of the card 
	 * @param cardValue the value of the card that means positive or negative movement 
	 */
	public Card(int cardNum, int cardValue)
	{
		this.cardNum = cardNum;
		this.cardValue = cardValue;  
		
	}
	
	/**
	 * Copy constructor
	 * @param c Card to copy
	 */
	public Card(Card c)
	{
		this(c.getCardNum() , c.getCardValue()); 
		
		/*cardNum = c.getCardNum();
		cardValue = c.getCardValue(); */
	}
	/**
	 * Accessor method
	 * @return cardNum the card number
	 */
	public int getCardNum()
	{
		return cardNum;
	}
	/**
	 * Accessor method
	 * @return cardValue the card value that translates to player movement
	 */
	public int getCardValue()
	{
		return cardValue;
	}
	/**
	 * Mutator method
	 * @param num the number of the card
	 */
	public void setCardNum(int num)
	{
		cardNum = num;
	}
	
	/**
	 * Mutator method
	 * @param val the value of the card
	 */
	public void setCardValue(int val)
	{
		cardValue = val;
	}

}

