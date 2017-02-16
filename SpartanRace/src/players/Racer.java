package players;
import spartanRace.Deck_of_Fortune;
import spartanRace.Wheel_of_Fortune;

/**
 * Child of Player. It has new attribute: energy and eliminated appropriate for a racing game.  
 * @author d_carrer
 *
 */
public class Racer extends Player {

	private int energy;
	boolean eliminated;

	/**
	 * Default constructor. Calls the class Player default constructor.
	 */
	public Racer()
	{
		// calls parent's default constructor implicitly
	}
	/**
	 * Constructor. Calls the class Player constructor that sets a name for the Racer.
	 * @param name of the racer
	 */
	public Racer(String name)
	{
		super(name);
	}
	/**
	 * Constructor. Calls the class Player constructor that sets a name and the position for the Racer.
	 * @param name that the racer will get
	 * @param pos that the racer will get
	 */
	public Racer(String name, int pos)
	{
		super(name, pos);
	}
	/**
	 * Constructor. Calls the class Player constructor that sets the name and position for the racer but
	 * it also initializes its own attribute energy
	 * @param name that the racer will get
	 * @param pos that the racer will get
	 * @param ene so that the attribute energy is set to this value
	 */
	public Racer(String name, int pos, int ene)
	{
		super(name, pos);
		energy = ene;
	}

	/**
	 * Accessor method
	 * @return energy attribute
	 */
	public int getEnergy()
	{
	    return energy;
	}
	/**
	 * Mutator method
	 * @param en (energy) to be set
	 */
	public void setEnergy(int en)
	{
	   energy = en;
	}
	/**
	 * Mutator method
	 * @param s to set if the player is eliminated or not
	 */
	public void setEliminated(boolean s)
	{
		eliminated = s;
	}

	/**
	 * Overrides the parent's method (class Player) so that the energy is added 30 points
	 * if the racer happens to pick a Card 
	 */
	public String pickCardFortune(Deck_of_Fortune deck)
	{
		String outToFile = "";
		String outTemp = "";
		
		outToFile = super.pickCardFortune(deck);
		energy += 30;
		System.out.print(outTemp = "Energy +30! Energy = "+getEnergy()+".");
		outToFile += " " + outTemp;
		
		return outToFile; 
	}
	/**
	 * Overrides the parent's method (class Player) so that the energy is doubled if the racer
	 * happens to spin the wheel of fortune. 
	 */
	public String spinWheelFortune(Wheel_of_Fortune wheel)
	{
		String outToFile = "";
		String outTemp = "";
		
		outToFile = super.spinWheelFortune(wheel);
		energy *= 2;
		System.out.print(outTemp = "Energy doubled! Energy = "+getEnergy()+".");
		outToFile += " " + outTemp;
		
		return outToFile;
	}

	/**
	 * Overrides the moveTo method from class Player to implement energy 
	 * substraction when the player moves
	 * This method moves the player to the next position.
	 * @param pos steps that the player is going to advance
	 */
	public String moveTo(int pos)
	{
		String outToFile = "";
		String outTemp = "";
		
		if(!isEliminated())
		{

			if(energy <= pos)
			{
				super.moveTo(energy);
				energy = 0;
				setEliminated(true);
				System.out.print(outTemp = " PLAYER ELIMINATED!!");
				outToFile += outTemp;
			}
			else
			{
				super.moveTo(pos);
				energy -= Math.abs(pos);
			}

		}
		
		return outToFile;


	}

	/*public void setPosition(int position)
	{
		super.setPosition(position);
		energy -= Math.abs(position);

		if(getEnergy()<= 0)
		{
			setEliminated(true);
			System.out.print(" PLAYER ELIMINATED!!");
		}
	}*/
	/**
	 * Returns the eliminated status, to check if the player is eliminated or not.
	 */
	public boolean isEliminated()
	{
		return eliminated;
	}



}
