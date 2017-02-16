package players;
import spartanRace.Card;
import spartanRace.Deck_of_Fortune;
import spartanRace.Wheel_of_Fortune;

/**
 * Abstract class player , Parent of all the other classes that play the game
 * @author d_carrer
 *
 */
abstract public class Player {

	private String name;
	private int position;
	private int myNum; // The player's number. Ex; Player 1 is the first player, Player 2 is the second and so on
	private static int numOfPlayers; // Static variable that counts the number of players
	//private int energy;

	/**
	 * Default constructor. Sets the name and positions to defaults.
	 */
	public Player()
	{
		name = "unknown";
		position = 0;
	}
    /**
     * Constructor. Sets the player's name.
     * @param name the player's name that we want to set 
     */
	public Player(String name)
	{
		this.name = name ;
		position = 0;
		if(numOfPlayers == 0)
		{
			numOfPlayers = 1;
			myNum = numOfPlayers;
		}
		else
		{
			numOfPlayers++;
			myNum = numOfPlayers;
		}
	}
	/**
	 * Constructor. Sets the player's name and position
	 * @param name
	 * @param position
	 */
	public Player(String name, int position)
	{
			this.name = name ;
			this.position = position;

			if(numOfPlayers == 0)
			{
				numOfPlayers = 1;
				myNum = numOfPlayers;
			}
			else
			{
				numOfPlayers++;
				myNum = numOfPlayers;
		    }
	}
	/**
	 * Accessor method
	 * This method returns the player's name.
	 * @return name of the player.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Accessor method.
	 * This method returns the player's position on the board.
	 * @return position of the player.
	 */
	public int getLocation()
	{
		return position;
	}

	/**
	 * Accessor method.
	 * This method returns the number of each player.
	 * @return myNum of the player(Player's number) 
	 */
	public int getMyNum()
	{
		return myNum;
	}

	/**
	 * Accessor method
	 * This method sets the player's name.
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	/**
	 * Mutator method
	 * This method sets the player's position.
	 * @param position to be set for the player
	 */
	public void setPosition(int position)
	{
		this.position = position;
	}

	/**
	 * This method moves the player to the next position.
	 * @param pos number of steps(cells) player is going to advance
	 */
	public String moveTo(int pos)
	{
		position += pos;
		return "";
	}
	
	/**
	 * This method is used when the player has already landed in a special position.
	 * It allows the player to move without draining energy according to the game specifications
	 * @param pos number of steps(cells) player is going to advance
	 */
	public void moveToWithoutEnergyLoss(int pos)
	{
		position += pos;
	}

	/**
	 * This method resets the amount of players to 0.
	 */
	public static void resetPlayers()
	{
		numOfPlayers = 0;
	}

	/**
		* This method sets the position of the player based on the card's value
		* he or she picked.
		* @param p player that picks a card from a deck
		* @param deck used by the the player p
		* @return string containing the output of the movements
		*/
			public String pickCardFortune(/*Player p,*/ Deck_of_Fortune deck)
			{
				String outToFile = "";
				String outTemp = "";
				
				Card card = deck.pickCard();
				System.out.print(outTemp = "Picking a card of fortune. current card " + card.getCardNum() + " ");
				outToFile += outTemp;

				if(card.getCardValue() == 0)
				{
					/*p.*/setPosition(0);
					System.out.print(outTemp = "Go back to position 0. ");
					outToFile += outTemp;
				}
				else
				{
					/*p.*/this.moveToWithoutEnergyLoss(card.getCardValue());
					if( card.getCardValue() < 0 )
					{
						System.out.print(outTemp = "Go back " + Math.abs(card.getCardValue()) + ". ");
						outToFile += outTemp;
					}
					else if( card.getCardValue() > 0)
					{
						System.out.print(outTemp = "Go forward " + card.getCardValue() + ". ");
						outToFile += outTemp;
					}
				}
				
				return outToFile; 
			}

	/**
		 * This method sets the position of the player based on value
		 * obtained by the wheel of fortune
		 * @param p An object of class player
		 * @param wheel A Wheel_of_Fortune object
		 */
		public String spinWheelFortune(/*Player p*/ Wheel_of_Fortune wheel)
		{
			String outToFile = "";
			String outTemp = "";
			
			int wheelResult = wheel.spinWheel();
			System.out.print(outTemp = "Spinning the wheel of fortune. ");
			outToFile += outTemp;

			if(wheelResult == 0)
			{
				/*p.*/setPosition(0);
				System.out.print(outTemp = "Go back to position 0. ");
				outToFile += outTemp;
			}
			else
			{
				/*p.*/this.moveToWithoutEnergyLoss(wheelResult);

				if( wheelResult < 0 )
				{
					System.out.print(outTemp = "Go back " + Math.abs(wheelResult) + ". ");
					outToFile += outTemp;
				}
				else if( wheelResult > 0)
				{
					System.out.print(outTemp = "Go forward " + wheelResult + ". ");
					outToFile += outTemp;
				}
			}
			
			return outToFile;
	}

	/**
	 * abstract method implemented in the children of this class
	 * @return
	 */
	abstract public boolean isEliminated();

	/**
	 * abstract method implemented in the children of this class
	 * @return
	 */
	abstract public int getEnergy();
	




}
