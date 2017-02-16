package players;
/**
 * This class is a child of racer. It starts at position 0 and has 130 energy points.
 * @author d_carrer
 *
 */
public class Perioikoi extends Racer {


	public Perioikoi()
	{
		// parent's default constructor called implicitly. Sets position to 0 by default
		super.setEnergy(130);  //calls parent to set Energy

	}

	public Perioikoi(String name)
	{
		super(name); // calls parent constructor and sets the name. Sets position to 0 by default
		super.setEnergy(130);
	}




}
