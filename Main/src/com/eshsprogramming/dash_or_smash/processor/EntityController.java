package com.eshsprogramming.dash_or_smash.processor;

import com.badlogic.gdx.utils.Array;
import com.eshsprogramming.dash_or_smash.DashOrSmash;
import com.eshsprogramming.dash_or_smash.model.entity.Entity;
import com.eshsprogramming.dash_or_smash.model.entity.pedestrian.PedestrianEntity;

/**
 * A
 * @Benjamin Landers
 */
public abstract class EntityController extends MultiTouchProcessor
{

	/**
	 * Creates a new MultiTouchProcessor instance. Also sets the max touches that it can deal with.
	 *
	 * @param maxTouches the max number of touches
	 */
	public EntityController(DashOrSmash game, int maxTouches)
	{
		super(game, maxTouches);
	}


}
