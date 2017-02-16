package cells;

public class Cell {
	
	private char cell;
	private int playersInCell;
	
	public Cell(char c)
	{
		cell = c;
		playersInCell = 0;
	}
	
	
	public char getCell()
	{
		return cell;
	}
	
	public int getPlayersInCell()
	{
		return playersInCell;
	}
	
	public void setCell(char c)
	{
		this.cell = c;
	}
	
	public void setPlayersInCell(int n)
	{
		playersInCell = n;
	}
	
	public void incrementPlayersInCell(int n)
	{
		playersInCell += n;
	}


}
