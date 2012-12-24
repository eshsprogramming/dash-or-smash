package com.eshsprogramming.nudistrailroadexhibition.controller;

import com.badlogic.gdx.utils.Array;
import com.eshsprogramming.nudistrailroadexhibition.model.Nudist;
import com.eshsprogramming.nudistrailroadexhibition.model.World;
import com.eshsprogramming.nudistrailroadexhibition.model.Train;

/**
 * @author Zachary Latta, Benjamin Landers
 */
public class WorldController
{
	private World world;
	private Array<Nudist> nudists; // I deleted the intialization because it was a memory leak
    private Array<Train> trains;

	public WorldController(World world)
	{
		this.world = world;
		this.nudists = world.getNudists();
        trains = world.getTrains();
	}

	/**
	 * The main update method.
	 */
	public void update(float delta)
	{
		processInput();

		// Calls update methods of nudists
		for(Nudist nudist : nudists)
		{
			nudist.update(delta);
		}
        for(Train train: trains)
        {
            train.update(delta);
        }
        checkCollision();
	}

	/**
	 * Changes the nudists' state and position based on input controls
	 */
	private void processInput()
	{
		for(Nudist nudist : nudists)
		{
		}
	}
    private void checkCollision()
    {
        for(Nudist nudist : nudists)
        {
            for(Train train: trains)
            {
                checkCollision(train,nudist);
            }

        }
    }
    private boolean checkCollision(Train train, Nudist nudist)
    {
        return false;
    }
}
