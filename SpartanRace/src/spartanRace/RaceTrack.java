package spartanRace;
import cells.Cell;
import cells.DeckCell;
import cells.JokerCell;
import cells.RegularCell;
import cells.SkipCell;
import cells.WheelCell;

/**
 * Initializes an array of type cell 
 * @author d_carrer
 *
 */
public class RaceTrack {
	
	private Cell[] racetrack;
	
	public RaceTrack(int size)
	{
		racetrack = new Cell[size];
		
		for(int i = 0; i < racetrack.length; i++)
		{
			
			if(i == 10 || i==30 || i==50 || i==70 || i==100 || i==130 || i==150 || i ==170)
			{
				racetrack[i] = new DeckCell();
			}
			else if( i==20 || i==55 || i==120 || i==155  )
			{
				racetrack[i] = new SkipCell();
			}
			else if(i==40 || i==60 || i==80 || i ==140 || i==160 || i==180)
			{
				racetrack[i] = new WheelCell();
			}
			else if( i==17 || i==37 || i==57 || i ==117 || i==137 || i==157 )
			{
				racetrack[i] = new JokerCell();
			}
			else
				racetrack[i] = new RegularCell();
		}
		
	}
	
	public char getCell(int i)
	{
		return racetrack[i].getCell();
	}
	
	public int getPlayersInCell(int i)
	{
		return racetrack[i].getPlayersInCell();
	}
	
	public int getLength()
	{
		return racetrack.length;
	}
	
	public void resetPositions()
	{
		for(int i = 0; i < racetrack.length; i++)
		{
			racetrack[i].setPlayersInCell(0);
		}
	}
	
	public void incrementNumOfPlayersInPosition(int index, int num)
	{
		racetrack[index].incrementPlayersInCell(num);
	}
	
	/**
	 * This method tells the player if they have landed on a special position on the board.
	 * @param i
	 * @return
	 */

	public boolean isSpecial(int i)
	{
		if(i>=(racetrack.length-1))
		{
			return false;
		}
		else
		{
			if( racetrack[i].getCell() == 'd'|| racetrack[i].getCell() == 's'|| racetrack[i].getCell() == 'w'|| racetrack[i].getCell() == '?')
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}
		
	private char getRegularPositionChar()
	{
		char defaultChar = ' ';
		
		for(int i = 0; i< racetrack.length; i++)
		{
			if(!this.isSpecial(i))
			{
				defaultChar = racetrack[i].getCell();
				break;
			}
		}
		
		return defaultChar; 
	}
	
	private String printLine(int start, int end)
	{
		String output = "";
		char defaultChar = this.getRegularPositionChar();

		for(int i=start; i <= end; i++)
		{
			if(racetrack[i].getPlayersInCell()>0)
				output += racetrack[i].getPlayersInCell()+" ";
			else
				output += defaultChar +" ";
		}

		output+= "\n";

		for(int i=start; i <= end; i++)
		{
			//output += " ";
			if(this.isSpecial(i))
			{
				output += racetrack[i].getCell();
			}
			else
				output += " ";
			
			output += " " ;

		}



		return output;

	}
	
	public String toString()
	{
		String output = "";
		
		output += printLine(1, 30);
		output += "\n\n";
		output += printLine(31, 60);
		output += "\n\n";
		output += printLine(61, 90);
		output += "\n\n";
		
		if(racetrack.length > 91)
		{
			output += printLine(91, 120);
			output += "\n\n";
			
			if(racetrack.length > 121)
			{
				
				output += printLine(121, 150);
				output += "\n\n";
				
				if(racetrack.length > 151)
				{
					output += printLine(151, 180);
					output += "\n\n";
					
					if(racetrack.length > 181)
					{
						output += printLine(181, 210);
						output += "\n\n";
					}
				}
				
				
			}
		}
		
		return output;
	}
	

}
