package players;

/**
 * This class is a child of racer. It starts at position 20 and has 100 energy points.
 * @author d_carrer
 *
 */
public class Spartiate extends Racer {


	public Spartiate()
	{
		// parent's default constructor called implicitly
		super.setPosition(20);
		super.setEnergy(100);

	}

	public Spartiate(String name)
	{
		super(name, 20, 100); // calls parent constructor and sets name, position and energy.
	}


}
