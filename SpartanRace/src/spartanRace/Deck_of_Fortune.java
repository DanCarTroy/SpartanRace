package spartanRace;

public class Deck_of_Fortune {
	
	private static final int SIZE = 10;
	final private Card[] cards;
	
	/**
	 * Default constructor. Initializes the array of card objects , and the sets
	 * each card number and their corresponding card value. 
	 */
	public Deck_of_Fortune()
	{
		cards = new Card[SIZE];
		

		cards[0] = new Card(1, -9);
		cards[1] = new Card(2, 0);
		cards[2] = new Card(3, -3);
		cards[3] = new Card(4, -8);
		cards[4] = new Card(5, 2);
		cards[5] = new Card(6, 1);
		cards[6] = new Card(7, 3);
		cards[7] = new Card(8, 0);
		cards[8] = new Card(9, -4);
		cards[9] = new Card(10, 6);
	}

	/**
	 * Picks a card from the top of the deck, pushes the other cards up and then it places the
	 * card on the bottom of the deck. The card number does not change since it is an attribute of the card 
	 * object and it has nothing to do with the index of the array
	 * @return card that has been picked up(copy of the original card, will be used to get the value of the card)
	 */

	public Card pickCard()
	{

		Card card = new Card(cards[0]); //new Card(cards[0].getCardNum() , cards[0].getCardValue());
		int i = 0;
		int j = 1;

		while( i < cards.length)
		{
			if( i == cards.length-1 )
			{
				cards[i] = card;
			}
			else
			{
				cards[i] = new Card(cards[j]); //(cards[j].getCardNum(), cards[j].getCardValue());
			}
			i++;
			j++;
		}

		return card;
	}

	/**
	 * Returns a copy of the deck (Not used in program but useful)
	 * @return
	 */

	public Card[] getDeck()
	{
		Card[] arr = new Card[cards.length];
		for(int i=0; i<cards.length; i++)
		{
			arr[i] = new Card(cards[i]);//new Card(cards[i].getCardNum(), cards[i].getCardValue());
		}
		return arr;
	}

	/**
	 *
	 * @param i index position
	 * @return card value
	 */

	public Card getCard(int i)
	{
		return this.cards[i]; //?
	}




	/**
	 * This method shuffles the deck. 
	 */

	public void shuffle()
	{
		int swapNum = Dice.randomGenerator(1,100);
		for(int i=0; i<swapNum; i++)
		{
			swap( Dice.randomGenerator(0,9) , Dice.randomGenerator(0,9) ); // Random number generator from the Dice class
		}
	}

	/**
	 * Swaps two cards, useful for shuffling the deck
	 * @param pos1 is the index of the first card
	 * @param pos2 is the index of the second card
	 */

	private void swap(int pos1, int pos2)
	{
		Card temp = new Card(cards[pos1]);
		cards[pos1] = new Card(cards[pos2]);
		cards[pos2] = new Card(temp);
		//temp = null;  check this
	}

	
	/*
	 * This method displays the value of each card for testing purposes.
	 */

	/*public void displayDeck()
	{
		for(int i=0; i<cards.length; i++)
		{
			System.out.println( this.getCard(i));
		}
	}*/



}
