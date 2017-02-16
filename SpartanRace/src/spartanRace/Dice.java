package spartanRace;
/** 
 * This class represents 2 dice and has its own Random Number Generator function	 
 * 
 * */
public class Dice {

	private int die1;
	private int die2;

	public Dice()
	{
		die1 = 1;
		die2 = 1;
	}

	public int getDie1()
	{
		return die1;
	}

	public int getDie2()
	{
		return die2;
	}

	/**
	 * This method assigns a random number between 1 and 6 to each die.
	 */
	public void throwDice()
	{
		die1 = randomGenerator(1,6);
		die2 = randomGenerator(1,6);
	}

	/**
	 * Generates random numbers
	 * @param min minimum generated number
	 * @param max maximum generated number
	 * @return
	 */
	protected static int randomGenerator(int min, int max)
	{
	   int range = Math.abs(max - min) + 1;
	   return (int)(Math.random() * range) + (min <= max ? min : max);
	}


}

