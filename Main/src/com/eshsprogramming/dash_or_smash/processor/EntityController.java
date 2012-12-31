package com.eshsprogramming.dash_or_smash.processor;

import com.eshsprogramming.dash_or_smash.DashOrSmash;

/**
 * A
 *
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
