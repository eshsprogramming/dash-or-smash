package com.eshsprogramming.dash_or_smash.model.entity.pedestrian.baddy;

import com.badlogic.gdx.math.Vector2;

/**
 * @author Zachary Latta
 */
public class BurglarBaddyPedestrianEntity extends BaddyPedestrianEntity
{
	/**
	 * The chance of a burglar spawning each frame out of 10000.
	 */
	public static final float SPAWN_CHANCE = 10f;

	/**
	 * Creates a new burglar.
	 *
	 * @param position The burglar's starting position.
	 */
	public BurglarBaddyPedestrianEntity(Vector2 position)
	{
		super(position, 250, 50);
	}
}
