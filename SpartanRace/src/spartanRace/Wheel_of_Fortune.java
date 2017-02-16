package spartanRace;


public class Wheel_of_Fortune {

	private static final int SIZE = 8;
	private int[] choices;

	public Wheel_of_Fortune()
	{
		choices = new int[SIZE];
		choices[0] = 1;
		choices[1] = 2;
		choices[2] = 0;
		choices[3] = -4;
		choices[4] = -6;
		choices[5] = -7;
		choices[6] = -8;
		choices[7] = -9;
	}

	/**
	 * This method spins the wheel.
	 * @return The result of the wheel.
	 */
	public int spinWheel()
	{
		return choices[Dice.randomGenerator(0,choices.length-1)];
	}



}

