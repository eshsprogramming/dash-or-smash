package com.eshsprogramming.nudistrailroadexhibition.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * @author Zachary Latta
 */
public class World
{
	/**
	 * An array of the blocks which make up the world.
	 */
	private Array<Block> blocks = new Array<Block>();
	/**
	 * An array of the nudists in the world.
	 */
	private Array<Nudist> nudists = new Array<Nudist>();

	public World()
	{
		createBlankWorld();
	}

	private void createBlankWorld()
	{
		// Adds nudists to the array of them
		for(int index = 0; index < 3; index++)
		{
			nudists.add(new Nudist(new Vector2(index, 3)));
		}

		// Adds blocks to the world
		for(int index1 = 0; index1 < 5; index1++)
		{
			for(int index2 = 0; index2 < 8; index2++)
			{
				blocks.add(new Block(new Vector2(index1, index2)));
			}
		}
	}
}
