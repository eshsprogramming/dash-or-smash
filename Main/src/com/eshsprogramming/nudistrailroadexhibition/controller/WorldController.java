package com.eshsprogramming.nudistrailroadexhibition.controller;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.eshsprogramming.nudistrailroadexhibition.model.Nudist;
import com.eshsprogramming.nudistrailroadexhibition.model.Train;
import com.eshsprogramming.nudistrailroadexhibition.model.World;

/**
 * The controller for the world. Manages sprite properties.
 *
 * @author Zachary Latta
 */
public class WorldController
{
	/**
	 * The world.
	 */
	private World world = null;
	/**
	 * The nudists in the world.
	 */
	private Array<Nudist> nudists = null;
	/**
	 * The trains in the world.
	 */
	private Array<Train> trains = null;
	/**
	 * The position of the user's touch.
	 */
	private Vector2 touchPosition = null;

	/**
	 * Creates a new world controller.
	 *
	 * @param world The world to be used in the world controller.
	 */
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
		checkTrainValidity();
	}

	/**
	 * Changes the nudists' state and position based on input controls
	 */
	private void processInput()
	{
		nudists.get(0).getPosition().x = touchPosition.x;
	}

	/**
	 * Checks if trains are allowed to be on the screen. Removes them if they aren't and creates a new one.
	 */
	private void checkTrainValidity()
	{
		for(Train train : trains)
		{
			if(train.getPosition().y + Train.SIZEY <  0)
			{
				trains.removeValue(train, true);
			}
		}

		while(trains.size < Train.NUMBER_OF_TRAINS)
		{
			trains.add(new Train(new Vector2(MathUtils.random(.5f,7f),
					MathUtils.random(5f,7f)), MathUtils.random(-.3f,-.7f)));
		}
	}

	private void checkCollision()
	{
		for(Nudist nudist : nudists)
		{
			for(Train train : trains)
			{
				if(checkCollision(train, nudist))
                {
					nudists.removeValue(nudist, true);
                }
			}
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
