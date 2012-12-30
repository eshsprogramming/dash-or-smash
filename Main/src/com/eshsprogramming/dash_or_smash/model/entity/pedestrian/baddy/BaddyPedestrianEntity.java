package com.eshsprogramming.dash_or_smash.model.entity.pedestrian.baddy;

import com.badlogic.gdx.math.Vector2;
import com.eshsprogramming.dash_or_smash.model.entity.pedestrian.PedestrianEntity;

/**
 * A baddy. Baddies increase the score when hit by vehicles and subtract from the score when a vehicle goes past the
 * screen without killing them.
 *
 * @author Zachary Latta
 */
public class BaddyPedestrianEntity extends PedestrianEntity
{
	/**
	 * The points to add to the score on death.
	 */
	public final int POINTS_ON_DEATH;
	/**
	 * The points to subtract from the score when a vehicle passes by without killing the baddy.
	 */
	public final int POINTS_ON_LIVE;

	/**
	 * The chance of the baddy spawning each frame out of 100.
	 */
	public static float SPAWN_CHANCE;

	/**
	 * Creates a new baddy.
	 *
	 * @param position The baddy's starting position.
	 */
	public BaddyPedestrianEntity(Vector2 position, int pointsOnDeath, int pointsOnLive)
	{
		super(position);

		this.POINTS_ON_DEATH = pointsOnDeath;
		this.POINTS_ON_LIVE = pointsOnLive;
	}
}
