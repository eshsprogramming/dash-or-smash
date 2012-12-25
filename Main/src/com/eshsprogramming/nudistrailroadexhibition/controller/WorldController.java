package com.eshsprogramming.nudistrailroadexhibition.controller;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.eshsprogramming.nudistrailroadexhibition.model.Nudist;
import com.eshsprogramming.nudistrailroadexhibition.model.Train;
import com.eshsprogramming.nudistrailroadexhibition.model.World;

/**
 * @author Zachary Latta
 */
public class WorldController
{
	private World world = null;
	private Array<Nudist> nudists = null;
	private Array<Train> trains = null;
	private Vector2 touchPosition = null;

	public WorldController(World world)
	{
		this.world = world;
		this.nudists = world.getNudists();
		this.touchPosition = world.getNudists().get(0).getPosition();
		this.trains = world.getTrains();
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

		// Calls update methods of trains
		for(Train train : trains)
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
		nudists.get(0).getPosition().x = touchPosition.x;
	}

	private void checkCollision()
	{
        Array<Nudist> toKill = new Array<Nudist>();
		for(Nudist nudist : nudists)
		{
			for(Train train : trains)
			{
				if(checkCollision(train, nudist))
                {
                toKill.add(nudist);
                }
			}
		}
       for(Nudist temp: toKill)
       {
           nudists.removeValue(temp, true);
       }
	}

	private boolean checkCollision(Train train, Nudist nudist)
	{
		return Intersector.intersectRectangles(train.getBounds(),nudist.getBounds());
	}

	/**
	 * Sets the touch position to the position given
	 *
	 * @param touchPosition
	 */
	public void setTouchPosition(Vector2 touchPosition)
	{
		this.touchPosition = touchPosition;
	}
}
