package com.eshsprogramming.dash_or_smash.model.world;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.eshsprogramming.dash_or_smash.DashOrSmash;
import com.eshsprogramming.dash_or_smash.model.entity.BlockEntity;
import com.eshsprogramming.dash_or_smash.model.entity.PedestrianEntity;
import com.eshsprogramming.dash_or_smash.model.entity.VehicleEntity;
import com.eshsprogramming.dash_or_smash.model.gui.Score;

/**
 * The game world.
 *
 * @author Zachary Latta, Benjamin Landers
 */
public class GameWorld extends World
{
	/**
	 * A reference to the actual Game instance.
	 */
	private DashOrSmash game = null;
	/**
	 * An array of the blocks which make up the world.
	 */
	private Array<BlockEntity> blockEntities = new Array<BlockEntity>();
	/**
	 * An array of the pedestrians in the world.
	 */
	private Array<PedestrianEntity> pedestrianEntities = new Array<PedestrianEntity>();
	/**
	 * An array of all the vehicleEntities
	 */
	private Array<VehicleEntity> vehicleEntities = new Array<VehicleEntity>();
	/**
	 * The player's score.
	 */
	private Score score = null;

	/**
	 * Creates a new world.
	 */
	public GameWorld(DashOrSmash game)
	{
		this.game = game;
		score = new Score("fonts/arial-15.fnt", false, game.gameScreen.getWidth() * 0.0035f,
				new Vector2(6.5f, 4.75f));
		createBlankWorld();
	}

	/**
	 * Fills the world with blockEntities.
	 */
	private void createBlankWorld()
	{
		// Adds pedestrians to the array of them
		for(int index = 0; index < 1; index++)
		{
			pedestrianEntities.add(new PedestrianEntity(new Vector2((int)(Math.random() * 6), 0), MathUtils.random(2)));
		}

		// Adds blocks to the world
		for(int index1 = 0; index1 < 8; index1++)
		{
			for(int index2 = 0; index2 < 5; index2++)
			{
				blockEntities.add(new BlockEntity(new Vector2(index1, index2)));
			}
		}
	}

	/**
	 * Returns the array of blockEntities in the world.
	 *
	 * @return The array of blockEntities in the world.
	 */
	public Array<BlockEntity> getBlockEntities()
	{
		return blockEntities;
	}

	/**
	 * Returns the array of pedestrianEntities in the world.
	 *
	 * @return The array of pedestrianEntities in the world.
	 */
	public Array<PedestrianEntity> getPedestrianEntities()
	{
		return pedestrianEntities;
	}

	/**
	 * Returns the array of vehicleEntities in the world.
	 *
	 * @return The array of vehicleEntities in the world.
	 */
	public Array<VehicleEntity> getVehicleEntities()
	{
		return vehicleEntities;
	}

	/**
	 * Returns the current score.
	 *
	 * @return The current score.
	 */
	public Score getScore()
	{
		return score;
	}
}
