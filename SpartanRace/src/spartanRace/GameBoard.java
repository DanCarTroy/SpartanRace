package spartanRace;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.PrintWriter; 
import java.io.File;
import java.io.FileNotFoundException; 

import players.Helot;
import players.Perioikoi;
import players.Player;
import players.Spartiate;

public class GameBoard {

	private static final int SIZE = 91;
	private RaceTrack racetrack; 
	private boolean debugging_mode;
	private boolean stopGame;

	/**
	 * Default constructor. Creates a Racetrack which is an array of Cell objects.  
	 */
	public GameBoard()
	{
		racetrack = new RaceTrack(SIZE);

	}
	
	/**
	 *  Constructor used for gameboards with different size.
	 * @param option  to pick the size of the gameboard
	 */
	public GameBoard(int option)
	{
		switch(option)
		{
			case 1: racetrack = new RaceTrack(91); break;
			case 2: racetrack = new RaceTrack(121); break;
			case 3: racetrack = new RaceTrack(151); break;
			case 4: racetrack = new RaceTrack(181); break;
			case 5: racetrack = new RaceTrack(211); break;
				default:  racetrack = new RaceTrack(91); break;
		}
	}

	/**
	 * This method sets the debugging mode.
	 * @param debug true for test mode , false for a normal game
	 */

	public void setDebuggingMode(boolean debug)
	{
		debugging_mode = debug;
	}

	/**
	 * This method checks the debugging status.
	 * @return debugging_mode false for a Normal game, true for a Test game(Debugging game)
	 */

	public boolean isDebugging()
	{
		return debugging_mode;
	}


	/**
	 * Testing purposes method to check the board
	 */

	public void testBoard()
	{
		for(int i = 0; i < racetrack.getLength(); i++)
		{
			System.out.println(racetrack.getCell(i));
		}

	}

	/**
	 * This method updates the players positions on the board after a turn,
	 * it also sets a flag to check when to stop the game if a player has
	 * won
	 * @param p Player object
	 */
	public void updatePositions(Player[] p)
	{
		racetrack = new RaceTrack(racetrack.getLength()); //Resets positions from previous turn
		int playerPos = 0;

		for(int i = 0; i < p.length; i++)
		{

			playerPos = p[i].getLocation();

			//Condition to win the game
			if( playerPos >= (racetrack.getLength() - 1) )
			{
				stopGame = true;
				racetrack.incrementNumOfPlayersInPosition( racetrack.getLength() - 1 , 1 ); //increments the Num of players in the winning position(ex. 90th pos)

			}
			else
			{
				racetrack.incrementNumOfPlayersInPosition( playerPos , 1 );  //increments the Num of players in the position where the player is located
			}

		}

	}

	/**
	 * This method display a winning message for those who won the game
	 * by checking if the stopGame value is true
	 * @param p Player object
	 */
	public String continueGame(Player[] p)
	{
		String outToFile = "";
		String outTemp = "";
		
		if(stopGame)
		{
			int numOfEliminated = 0;
			for(int i = 0; i < p.length; i++)
			{
				if(p[i].isEliminated())
				{
					numOfEliminated++;
				}
			}

			if(numOfEliminated == p.length)
			{
				System.out.println(outTemp = "ALL PLAYERS ARE ELIMINATED!! GAME OVER!!!");
				outToFile += outTemp + "\n";
			}
			else
			{

				System.out.println(outTemp = "We have " + racetrack.getPlayersInCell(racetrack.getLength() - 1)+" winner/s!");
				outToFile += outTemp + "\n";
				
				if(racetrack.getPlayersInCell(racetrack.getLength() - 1) != 0)
				{	
					System.out.println(outTemp = "Congratulations to: ");
					outToFile += outTemp + "\n";
					
					for(int i = 0; i < p.length; i++)
					{
						if( p[i].getLocation() >= (racetrack.getLength() - 1) )
						{
							System.out.println(outTemp = "\t"+p[i].getName());
							outToFile += outTemp + "\n";
						}
					}
				}
			}


		}
		
		return outToFile;
	}

	/**
	 * Plays a turn for an individual player. Accepts all the elements/objects that are involved
	 * in the gameplay of the game. It makes the player use those objects to accomplish the movement
	 * of his or her position.
	 * @param p player that will play the turn and will use the other objects
	 * @param dice that will be thrown by the player p 
	 * @param deck that will be used by the player p to pick cards
	 * @param wheel that will be spinned by the player p
	 */
	public String playTurn(Player p, Dice dice, Deck_of_Fortune deck, Wheel_of_Fortune wheel )
	{
		Scanner kb = new Scanner(System.in);
		String outToFile = ""; // String that will be outputted to a file
		String outTemp = "";

		int player_pos = 0;
		boolean obstacle = false;
		int pre_position = 0;
		
		// Normal Game
		
			
			int myPlayerNum = 0;
			String myPlayeIn = "";
			do
			{
				// Rolling the dice
				do
				{
					System.out.println("Player "+p.getName()+ " enter a "+p.getMyNum()+" to roll the dice "  );
					myPlayeIn = kb.next();
					if(!myPlayeIn.matches("-?\\d+"))
						System.out.println("Please enter a number.");
				}while(!myPlayeIn.matches("-?\\d+"));
				myPlayerNum = Integer.parseInt(myPlayeIn);
	
				if(myPlayerNum != p.getMyNum())	
					System.out.println("Please write your player number("+p.getMyNum()+" ) and press Enter to roll the dice");
			
			}while(myPlayerNum != p.getMyNum());
			
			dice.throwDice();
			pre_position = dice.getDie1()+dice.getDie2();
			outToFile += p.moveTo(pre_position); //Move action by current player

			// Output to the screen 
			System.out.print(outTemp = p.getName()+" rolled a "+dice.getDie1()+ " and a "+dice.getDie2()+ ". "+p.getName()+" is at location "+p.getLocation()+". ");
			outToFile += outTemp;
			System.out.print(outTemp = "Energy = "+(p.getEnergy()+pre_position)+"-" +pre_position+" = "+p.getEnergy()+" .");
			outToFile += outTemp;
			//System.out.print("Energy -"+pre_position+" ! Energy = "+p.getEnergy()+".");


		 


		/* Initializes a variable player_pos to the current location of the player to avoid  
		   writing p.getLocation() all the time.  */
		if(p.getLocation() == 0)
		player_pos = 0; // Fix for setting position to 0 in debugging mode
		else
		player_pos = p.getLocation();  

		
		/* Checks if the player has landed in a special position to execute the right action 
		   and it also checks  if it is not landing in a special
		   position twice in the same turn (obstacle == false)     */
		if(racetrack.isSpecial(player_pos) && (obstacle==false) &&(!p.isEliminated()))
		{
			if(!this.isDebugging())   // Fix for debugging mode multiple special locations
			obstacle = true;	      // and For Normal Game it sets obstacle to true to avoid a player executing
									  // a special position action more than one in the same turn

			if (racetrack.getCell(player_pos)=='d')
			{
				outToFile += p.pickCardFortune(deck);
			}
			else if (racetrack.getCell(player_pos)=='s')
			{
				if(p.getLocation()==120 || p.getLocation()==155)
					p.setPosition(168);
				else
					p.setPosition(68);
				
				System.out.print(outTemp = "Found a shorcut! Moving to position 68. ");
				outToFile += outTemp;
			}
			else if (racetrack.getCell(player_pos)=='w')
			{
				outToFile += p.spinWheelFortune(wheel);
			}
			else if (racetrack.getCell(player_pos)=='?')
			{
				int input = 0;
				String inputIn = "";
				do
				{
					do
					{
						System.out.println("Enter 1 to spin the wheel or 2 to pick a card:  ");
						inputIn = kb.next();
						if(!inputIn.matches("-?\\d+"))
							System.out.println("Please enter a number.");
					}while(!inputIn.matches("-?\\d+"));
					input = Integer.parseInt(inputIn);
					
					if(input==1)
					{
						outToFile += p.spinWheelFortune(wheel);
					}
					else if( input == 2 )
					{
						outToFile += p.pickCardFortune(deck);
					}
					else
					{
						System.out.println("You did not enter 1 or 2.");
					}
				}while( !((input >= 1) && (input <= 2)));
			}

			System.out.println(outTemp = "You are at location " + p.getLocation());
			outToFile += outTemp + "\n";
		}
		else
		{
			System.out.println();
			outToFile += "\n";
		}
		
		//kb.close();
		return outToFile;
	}

	/**
	 * Main Logic behind the game. Runs the game!! All the statements that could be written in the main method
	 * are written here instead. 
	 */
	public void runGame()
	{
		String outToFile = "";
		String outTemp = "";
		PrintWriter pw = null;
		try
		{
			pw = new PrintWriter("raceout.txt");
		}
		catch(FileNotFoundException e) 
		{
			System.err.println("File not found or cannot create file");
			System.exit(0);
		}
		
		
		int playAgain = 0;
		Scanner kb = new Scanner(System.in);
		// Loop for playing again another game
		do
		{
			// Welcoming message and asking to run a real-time or a debugging game
			System.out.println(outToFile = "Welcome to spartan Race!");
			
			//int numOfPlayers = 0;
			
			// Option to run Test Mode (Debugging mode)
				System.out.println("GAME MODE---> Enter 2 for DEBUGGING mode or anything else for a NORMAL GAME: ");
				String input = "";
				int debugNum = 0;
				
				input = kb.next();
				try {
					debugNum = Integer.parseInt(input);
					if(debugNum == 2)
						this.setDebuggingMode(true);
				}
				catch(NumberFormatException e){
				}
				
				if(this.isDebugging())
				{
					outToFile += runDebuggingGame();
				}
				else
				{
					outToFile += runNormalGame();
				}
					
			
			pw.println(outToFile);
			
			
			// Play Again?
			String playAgainIn = "";
			do
			{
				System.out.println("Would you like to play again? (1 for yes, other number for no)" );
				playAgainIn = kb.next();
				
				if(!playAgainIn.matches("-?\\d+"))
					System.out.println("Please enter a number.");
				
			}while(!playAgainIn.matches("-?\\d+"));
			
			playAgain = Integer.parseInt(playAgainIn);
			
			if(playAgain != 1 )
			{
				//stopGame = true;
				System.out.println("--> Hope you enjoyed Version 3.0 of the Spartan Race game. <--");
			}
			else
			{
				stopGame = false;
				setDebuggingMode(false);
			}


		}while(playAgain == 1);
		
		pw.close();
		kb.close();



	}
	
	private String runDebuggingGame()
	{
		String outTemp = "";
		String outToFile = "";
		Scanner kb = new Scanner(System.in);
		
		System.out.println(outTemp = "Debugging Mode (Test Mode) :");
		outToFile += "\n"+outTemp+"\n";
		
			String debugOptionIn = "";
			int debugOption = -1;
			do
			{
				System.out.println("Press 1 to read default input file(debug_input.txt) OR  press 2 to specify another file name and location:  ");
				debugOptionIn = kb.next();
				if(!debugOptionIn.matches("-?\\d+"))
					System.out.println("Please enter a number.");
				else
					debugOption = Integer.parseInt(debugOptionIn);
				
			}while((!debugOptionIn.matches("-?\\d+")) || ( debugOption<1 || debugOption >2  ));
			
			File file = null;
			if(debugOption == 1)
			{
				file = new File("debug_input.txt");
			}
			else if(debugOption == 2)
			{
				System.out.println("Enter input filename: ");
				file = new File(kb.next());
			}
			
			if(!file.exists())
			{
				System.out.println("File does not exist. Please create an input file. The format of the input file will be follows:\n"
						+ "First, the number of players will be indicated(then skip a line), "
						+ "followed by the name of player #1(then skip a line), type of player #1(skip a line), name of player #2(skip a line),"
						+ " type of player #2(skip a line) (…include entries for all players).\n"
						+ "Then the moves of all players will be specified in the following fashion:\n"
						+ "dievalue1 dievalue2 jokerchoice (skip a line)"
						+ "dievalue1 dievalue2 jokerchoice (skip a line)");
				System.exit(0);
			}
			Scanner scan = null;
			try
			{
				scan = new Scanner(file);
			}
			catch(FileNotFoundException e)
			{
				System.err.println("File not found, not accessible or does not exits! Try again.");
				System.exit(0);
			}
			
			
			//Asking for the number of players that will play the game
				String numOfPlaye = "";
				int numOfPlayers = 0;
				
					numOfPlaye = scan.nextLine();    // Reads first line of file
					if(!numOfPlaye.matches("-?\\d+"))
					{
						System.out.println("Number of players is not a number. Please fix input file.");
						System.exit(0);
					}
					numOfPlayers = Integer.parseInt(numOfPlaye);
					
					//Checks if the number of players is between 2 and 4
					if(numOfPlayers<=1 || numOfPlayers>4)
					{
						System.out.println("Invalid number of players. Please fix or enter another input file. ");
						System.exit(0);
					}
					
					System.out.println(outTemp = "Num of Players: "+numOfPlayers);
					outToFile += outTemp + "\n";
					
					// Array of players initialization 
					Player.resetPlayers();
					Player[] players = new Player[numOfPlayers];
					
					// Assigns players' name and class 
					for(int i =0; i<numOfPlayers; i++)
					{
						String name = "";
						String pclass = "";
						char ch = ' ';

						//Asking for the Name
							name = scan.nextLine();
							if(!name.matches("[a-zA-Z]+"))
							{
								System.out.println("The name can only be alphabetical characters.");
								System.exit(0);
							}
							
						//Asking for player's class
							pclass = scan.nextLine();
							ch = pclass.charAt(0);

							switch(ch)
							{
								case 'S': case 's': case '1': players[i] = new Spartiate(name); break;
								case 'H': case 'h': case '2': players[i] = new Helot(name); break;
								case 'P': case 'p': case '3': players[i] = new Perioikoi(name); break;
								default: System.err.println("Not a right character, fix the player class field with (S, H or P) OR (1, 2 or 3) and Restart the game "); System.exit(0);
							}
							
							System.out.println(outTemp = players[i].getName() + " is a "+players[i].getClass()+".");
								outToFile += outTemp + "\n"; 
							
					}
					
					System.out.println("Welcome racers ... how many of you will be sold as slaves today ....hahaha.");
					System.out.println("Ready ... Set ... Go!");

					// Variable Declaration 
					Deck_of_Fortune deck = new Deck_of_Fortune();
					deck.shuffle(); //Shuffles the deck 
					Wheel_of_Fortune wheel = new Wheel_of_Fortune();
					Dice dice = new Dice();

					//Loop that  makes players play their respective turn if they are not eliminated
					
					while(!stopGame) // condition that stops the game if all players are eliminated
					{
						int elimCount = 0;

						while(scan.hasNextLine())
						{
							for(int i =0; i<numOfPlayers; i++)
							{
								if(players[i].isEliminated())
								{
									elimCount++;
									if(elimCount == players.length)
									{
										stopGame = true;
										break;
									}
								}
								else
								{
									
										outToFile += playDebuggingTurn(players[i], dice, deck, wheel, scan );
									
								}
	
							}
							// Updates players' positions in the racetrack before printing
							updatePositions(players);

							if(!stopGame)
							{
								//System.out.println(racetrack); // Prints the racetrack if the game is still running
								outToFile += racetrack.toString() + "\n"; 
							}

							outToFile += continueGame(players);    // Checks if a player has won the game
							if(!scan.hasNextLine())
							{
								System.out.println(outTemp = "The input file is over. No more input");
								outToFile += outTemp +"\n";
								stopGame = true;
							}
							
							if(stopGame == true)
								break;
							
						}
						
					

					}
				
			return outToFile;
	}
	
	private String runNormalGame()
	{
		Scanner kb = new Scanner(System.in);
		int numOfPlayers = 0;
		
		String outTemp = "";
		String outToFile = "";
		
		System.out.println(outTemp = "Normal Game:");
		outToFile += outTemp + "\n";
		
		//Asking for the number of players that will play the game
		do	
		{
			String numOfPlaye = "";
			do
			{
				System.out.print("Please, choose the number of players and press Enter: ");
				numOfPlaye = kb.next();
				if(!numOfPlaye.matches("-?\\d+"))
					System.out.println("Please enter a number.");
			}while(!numOfPlaye.matches("-?\\d+"));
		
			numOfPlayers = Integer.parseInt(numOfPlaye);
						
			if(numOfPlayers<=1 || numOfPlayers>4)
				System.out.println("You have chosen an invalid number of players. Please choose between 2 and 4 players. ");
			
		}while(numOfPlayers>4 || numOfPlayers<=1 ); //Checks if the number of players is between 2 and 4
		
		System.out.println(outTemp = "Num of Players: "+numOfPlayers);
		outToFile += outTemp + "\n";
		
		// Array of players initialization 
		Player.resetPlayers();
		Player[] players = new Player[numOfPlayers];

		
		System.out.println("There are 3 classes of racers: -Spartiate(start in position 20 with 100 energy points)\n-Helot(start in position 40 with 60 energy points)\n-Perioikoi(start in position 0 with 130 energy points) ");

		// Assigns players' name and class 
		for(int i =0; i<numOfPlayers; i++)
		{
			String name = "";
			String pclass = "";
			char ch = ' ';

			//Asking for the Name
			do{
				System.out.println("Enter Player "+(i+1)+" name: ");
				name = kb.next();
				if(!name.matches("[a-zA-Z]+"))
					System.out.println("The name can only be alphabetical characters.");
			}while(!name.matches("[a-zA-Z]+"));
			
			//Asking for player's class
			do
			{
				System.out.println("Enter Player "+(i+1)+"("+name+") class, press S for Spartiate, H for Helot or P for Perioikoi and press Enter: ");
				pclass = kb.next();
				ch = pclass.charAt(0);

				switch(ch)
				{
					case 'S': case 's': players[i] = new Spartiate(name); break;
					case 'H': case 'h': players[i] = new Helot(name); break;
					case 'P': case 'p': players[i] = new Perioikoi(name); break;
					default: System.out.println("Not a right character, type S, H or P"); break;
				}

		 	}while( !(ch=='S' || ch=='s' || ch=='H' || ch=='h' || ch=='P' || ch=='p') );//( ch!='S' && ch!='s' && ch!='H' && ch!='h' && ch!='P' && ch!='p' );


		}
		
		
		System.out.println("Welcome racers ... how many of you will be sold as slaves today ....hahaha.");
		System.out.println("Ready ... Set ... Go!");

		
		// Variable Declaration 
		Deck_of_Fortune deck = new Deck_of_Fortune();
		deck.shuffle(); //Shuffles the deck 
		Wheel_of_Fortune wheel = new Wheel_of_Fortune();
		Dice dice = new Dice();

		//Loop that  makes players play their respective turn if they are not eliminated
		
		while(!stopGame) // condition that stops the game if all players are eliminated
		{
			int elimCount = 0;

			for(int i =0; i<numOfPlayers; i++)
			{
				if(players[i].isEliminated())
				{
					elimCount++;
					if(elimCount == players.length)
					{
						stopGame = true;
						break;
					}
				}
				else
				{
					outToFile += playTurn(players[i], dice, deck, wheel );
				}

			}
			
			// Updates players' positions in the racetrack before printing
			updatePositions(players);

			if(!stopGame)
			{
				//System.out.println(racetrack); // Prints the racetrack if the game is still running
				outToFile += racetrack.toString() + "\n"; 
			}

			outToFile += continueGame(players);    // Checks if a player has won the game

		}
		
		return outToFile;
	}
	
	public String playDebuggingTurn(Player p, Dice dice, Deck_of_Fortune deck, Wheel_of_Fortune wheel, Scanner scan )
	{
		String outToFile = ""; // String that will be outputted to a file
		String outTemp = "";
		
		int player_pos = 0;
		boolean obstacle = false;
		int pre_position = 0;
		
		String line = "";
		try
		{
			line = scan.nextLine();
		}
		catch(NoSuchElementException e)
		{
			System.err.println("No more inputs in the file. Ending game!");
			System.exit(0);
		}
		
		Scanner lineScan = new Scanner(line);
		
		int die1 = 0;
		int die2 = 0;
		String die1In = "";
		String die2In = "";
		
		String jokerIn = "";
		char jokerOp = ' ';
		String myPlayeIn = "";
		
		die1In = lineScan.next(); //System.out.println("die1in : "+die1In);
		//System.out.println(lineScan.next());
		die2In = lineScan.next();
		//System.out.println("die2in : "+die2In);
		try
		{
			jokerIn = lineScan.next();
		}
		catch(NoSuchElementException e)
		{
			System.err.println("The input file contains a wrong joker token or it does not have a joker token for one of the instructions\n" +
					"Please fix the input file, enter c or w in the joker field as specified and restart the game.");
			System.exit(0);		
		}
		//System.out.println("jokerIn : "+jokerIn);
		
		try
		{
			die1 = Integer.parseInt(die1In);
			die2 = Integer.parseInt(die2In);
		}
		catch(NumberFormatException e)
		{
			System.err.println("The die values can only be numbers. Fix input file and restart game.");
			System.exit(0);
		}
		
		try 
		{
			if((die1<1 || die1>6) || (die2<1 || die2>6))
				throw new DieValueOutOfBoundsException();
		}
		catch(DieValueOutOfBoundsException e)
		{
			System.err.println(e.getMessage());
			System.exit(0);
		}
		
		jokerOp = jokerIn.charAt(0);
		//System.out.println("jokerOp = "+jokerOp);
		
		try
		{
			if(jokerOp != 'c' && jokerOp != 'w' )
				throw new NoSuchJokerException();
		}
		catch(NoSuchJokerException e)
		{
			System.err.println(e.getMessage());
			System.exit(0);
		}
		
		pre_position = die1+die2;
		outToFile += p.moveTo(pre_position); //Move action by current player

		// Output to the screen 
		System.out.print(outTemp = p.getName()+" rolled a "+die1+ " and a "+die2+ ". "+p.getName()+" is at location "+p.getLocation()+". ");
		outToFile += outTemp;
		System.out.print(outTemp = "Energy = "+(p.getEnergy()+pre_position)+"-" +pre_position+" = "+p.getEnergy()+" .");
		outToFile += outTemp;
		//System.out.print("Energy -"+pre_position+" ! Energy = "+p.getEnergy()+".");


	/* Initializes a variable player_pos to the current location of the player to avoid  
	   writing p.getLocation() all the time.  */
	if(p.getLocation() == 0)
	player_pos = 0; // Fix for setting position to 0 in debugging mode
	else
	player_pos = p.getLocation();  

	
	/* Checks if the player has landed in a special position to execute the right action 
	   and it also checks  if it is not landing in a special
	   position twice in the same turn (obstacle == false)     */
	if(racetrack.isSpecial(player_pos) && (obstacle==false) &&(!p.isEliminated()))
	{
		if(!this.isDebugging())   // Fix for debugging mode multiple special locations
		obstacle = true;	      // and For Normal Game it sets obstacle to true to avoid a player executing
								  // a special position action more than one in the same turn

		if (racetrack.getCell(player_pos)=='d')
		{
			outToFile += p.pickCardFortune(deck);
		}
		else if (racetrack.getCell(player_pos)=='s')
		{
			if(p.getLocation()==120 || p.getLocation()==155)
				p.setPosition(168);
			else
				p.setPosition(68);
			
			System.out.print(outTemp = "Found a shorcut! Moving to position 68. ");
			outToFile += outTemp;
		}
		else if (racetrack.getCell(player_pos)=='w')
		{
			outToFile += p.spinWheelFortune(wheel);
		}
		else if (racetrack.getCell(player_pos)=='?')
		{
			if(jokerOp=='w')
			{
				outToFile += p.spinWheelFortune(wheel);
			}
			else if( jokerOp == 'c' )
			{
				outToFile += p.pickCardFortune(deck);
			}
		}

		System.out.println(outTemp = "You are at location " + p.getLocation());
		outToFile += outTemp + "\n";
	}
	else
	{
		System.out.println();
		outToFile += "\n";
	}
	
		return outToFile;
	}


}



class DieValueOutOfBoundsException extends Exception
{
	public DieValueOutOfBoundsException()
	{
		super("One of the dice have a wrong value. Value must be between 1 and 6!");
	}
	
	public DieValueOutOfBoundsException(String message)
	{
		super(message);
	}
	
	
}

class NoSuchJokerException extends Exception
{
	public NoSuchJokerException()
	{
		super("Joker has a wrong value. Value must be between c or w! Fix input file and try again.");
	}
	
	public NoSuchJokerException(String message)
	{
		super(message);
	}
	
	
}
















