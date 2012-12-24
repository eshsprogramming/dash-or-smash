package com.eshsprogramming.nudistrailroadexhibition.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * @author Zachary Latta, Benjamin Landers
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
    /**
     * An array of all the trains
     */
    private Array<Train> trains = new Array<Train>();

	public World()
	{
		createBlankWorld();
	}

	private void createBlankWorld()
	{
		// Adds nudists to the array of them
		for(int index = 0; index < 3; index++)
		{
			nudists.add(new Nudist(new Vector2(index, 0)));
		}

		// Adds blocks to the world
		for(int index1 = 0; index1 < 8; index1++)
		{
			for(int index2 = 0; index2 < 5; index2++)
			{
				blocks.add(new Block(new Vector2(index1, index2)));
			}
		}
	}

	public Array<Block> getBlocks()
	{
		return blocks;
	}

	public Array<Nudist> getNudists()
	{
		return nudists;
	}
    public Array<Train> getTrains()
    {
        return trains;
    }
}
