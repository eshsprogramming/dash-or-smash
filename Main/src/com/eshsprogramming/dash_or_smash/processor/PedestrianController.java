package com.eshsprogramming.dash_or_smash.processor;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.eshsprogramming.dash_or_smash.DashOrSmash;
import com.eshsprogramming.dash_or_smash.model.entity.PedestrianEntity;

import javax.swing.text.Position;

/**
 * IMproved version of mutlitouch processor that can better deal with controlling nudists
 * @author benjamin Landers
 */
public class PedestrianController extends MultiTouchProcessor
{
	PedestrianEntity[] controlled = null;
	/**
	 * Creates a new MultiTouchProcessor instance. Also sets the max touches that it can deal with.
	 *
	 * @param maxTouches the max number of touches
	 */
	public PedestrianController(DashOrSmash game, int maxTouches)
	{
		super(game, maxTouches);
		controlled = new PedestrianEntity[maxTouches];
	}
	public void updatePedestrians(Array<PedestrianEntity> pedestrians)
	{
		for(Vector2 temp: getPositions())
		{
			if(temp.x == -5 || temp.y == -5)
				continue;
			for(PedestrianEntity pedestrian: pedestrians)
			{
				if(temp.x < pedestrian.getPosition().x+PedestrianEntity.SIZEX && temp.x > pedestrian.getPosition().x)
				{
					pedestrian.getPosition().x = temp.x -PedestrianEntity.SIZEX/2;
				}
			}
		}
	}
}
