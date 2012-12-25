package com.eshsprogramming.nudistrailroadexhibition.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.eshsprogramming.nudistrailroadexhibition.model.Nudist;
import com.eshsprogramming.nudistrailroadexhibition.model.Train;
import com.eshsprogramming.nudistrailroadexhibition.model.World;
import com.eshsprogramming.nudistrailroadexhibition.view.WorldRenderer;

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
	 * The hurt sound. Played when a nudist dies.
	 */
	private Sound hurtSound = null;

	/**
	 * Creates a new world controller.
	 *
	 * @param world The world to be used in the world controller.
	 */
	public WorldController(World world)
	{
		this.world = world;
		this.nudists = world.getNudists();
		this.trains = world.getTrains();
		this.touchPosition = world.getNudists().get(0).getPosition();
		this.hurtSound = Gdx.audio.newSound(Gdx.files.internal("sounds/hurt.wav"));
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
			trains.add(new Train(new Vector2(MathUtils.random(0f, WorldRenderer.CAMERA_WIDTH - Train.SIZEX),
					MathUtils.random(5f, 10f)), MathUtils.random(-0.8f, -3f)));
		}
	}

	private void checkCollision()
	{
		for(int index1 = 0; index1 < trains.size; index1++)
		{
			for(int index2 = 0; index2 < nudists.size; index2++)
			{
				if(checkCollision(trains.get(index1), nudists.get(index2)))
                {
					hurtSound.play();
					nudists.removeIndex(index2);
					touchPosition = nudists.get(0).getPosition();
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
