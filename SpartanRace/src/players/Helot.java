package players;
/**
 * This class is a child of racer. It starts at position 40 and has 60 energy points.
 * @author d_carrer
 *
 */
public class Helot extends Racer {


	public Helot()
	{
		// parent's default constructor called implicitly
		super.setPosition(40);
		super.setEnergy(60);

	}

	public Helot(String name)
	{
		super(name, 40, 60); // calls parent constructor and sets name, position, and energy.
	}







}

