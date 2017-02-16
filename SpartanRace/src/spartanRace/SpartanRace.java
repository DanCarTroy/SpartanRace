package spartanRace;

import java.util.InputMismatchException;
import java.util.Scanner;
/*
 * Comp 249/Winter 2014
 * Assignment #2
 * Written by: Daniel Carrera(6729886) Uploaded to GitHub on Feb, 2017
 * Date: February 2014
 */

/* This program simulates a game called Spartan Race.  
 * Spartan Race is a type of obstacle race where all players start at the same position, 
 * and must run on a course where various obstacles are hidden.  
 * The player who arrives at the finish line first wins the race, 
 * while the others were sold as slaves.  
 * Changes made in version 3 include : Input and output to files and error checking
 * plus an option to choose between 5 different game board sizes. 
 */

public class SpartanRace
{

	public static void main(String[]args)
	{
		Scanner kb = new Scanner(System.in);
		int boardSize = 0;
		
		System.out.println("/*******************************************************************" +
				         "\n *  ____  __    __   __  ___  __  __  _    ___   __   ___  ___     *" +
				         "\n * /___  | _)  /__\\ | _)  |  /__\\ | \\ |    | _) /__\\ /    |__      *" +
				         "\n * ___/  |  \\ /    \\|  \\  | /    \\|  \\|    |  \\/    \\\\___ |___     *" +
				         "\n\n *****************************************************************") ;
		
		
		System.out.print("Choose game board size: Press\n1 for size 90 \n2 for size 120 \n3 for size 150 " +
				"\n4 for size 180 \n5 for size 210\nand press Enter: ");
		do
		{
			try
			{
				boardSize = kb.nextInt();
			}
			catch(InputMismatchException e)
			{
				System.out.println("Restart the game and choose a correct option.");
				System.exit(0);
			}
			
			if(boardSize < 1 || boardSize > 4)
				System.out.print("Please enter one of the following options: 1, 2 ,3, 4 or 5:");
			
		}while(boardSize < 1 || boardSize > 4);
			
		GameBoard game;
		
		switch(boardSize)
		{
			case 1: game = new GameBoard(1); break;
			case 2: game = new GameBoard(2); break;
			case 3: game = new GameBoard(3); break;
			case 4: game = new GameBoard(4); break;
			case 5: game = new GameBoard(5); break;
				default:  game = new GameBoard(1); break;
		}
		
		game.runGame();
		
		kb.close();

	}
}

