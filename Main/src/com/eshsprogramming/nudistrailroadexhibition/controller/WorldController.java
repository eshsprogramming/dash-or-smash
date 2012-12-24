package com.eshsprogramming.nudistrailroadexhibition.controller;

import com.badlogic.gdx.utils.Array;
import com.eshsprogramming.nudistrailroadexhibition.model.Nudist;
import com.eshsprogramming.nudistrailroadexhibition.model.World;

/**
 * @author Zachary Latta
 */
public class WorldController
{
	private World world;
	private Array<Nudist> nudists = new Array<Nudist>();

	public WorldController(World world)
	{
		this.world = world;
		this.nudists = world.getNudists();
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
}
