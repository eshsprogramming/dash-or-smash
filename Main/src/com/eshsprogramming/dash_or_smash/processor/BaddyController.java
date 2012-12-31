package com.eshsprogramming.dash_or_smash.processor;

import com.badlogic.gdx.utils.Array;
import com.eshsprogramming.dash_or_smash.DashOrSmash;
import com.eshsprogramming.dash_or_smash.model.entity.pedestrian.PedestrianEntity;
import com.eshsprogramming.dash_or_smash.model.entity.pedestrian.baddy.BaddyPedestrianEntity;
import com.eshsprogramming.dash_or_smash.model.entity.pedestrian.baddy.BurglarBaddyPedestrianEntity;

/**
 * Controller for the baddies
 * @author Benjamin Landers
 */
public class BaddyController extends EntityController
{


	/**
	 * Creates a new MultiTouchProcessor instance. Also sets the max touches that it can deal with.
	 *
	 * @param maxTouches the max number of touches
	 */
	public BaddyController(DashOrSmash game, int maxTouches)
	{
		super(game, maxTouches);
	}

	/**
	 * updates the position of the baddies
	 * @param baddies the array of baddies
	 * @param peds the array of pedestrians that are going to influence the baddies
	 */
	public void updateBaddies(Array<BaddyPedestrianEntity> baddies, Array<PedestrianEntity> peds)
	{
		float temp = 0, temp2 = 0, speedLimit = .25f , runSpeed = .02f;
		boolean isNexttoPeople = false, isClosetoPeople = false;;

		for(BaddyPedestrianEntity bad: baddies)
		{
			for(PedestrianEntity goodPed: peds)
			{
				isNexttoPeople |= (bad.getPosition().x  + temp2  + .5f + BurglarBaddyPedestrianEntity.SIZEX
						>  goodPed.getPosition().x &&
						bad.getPosition().x  + temp2  - .5f <  goodPed.getPosition().x +PedestrianEntity.SIZEX);
				isClosetoPeople |= (bad.getPosition().x  + temp2  + .2f + BurglarBaddyPedestrianEntity.SIZEX
						>  goodPed.getPosition().x &&
						bad.getPosition().x  + temp2  - .2f <  goodPed.getPosition().x +PedestrianEntity.SIZEX);
				temp = ((isNexttoPeople)?((isClosetoPeople)?.001f:.005f):.03f) / (goodPed.getPosition().x -bad.getPosition().x);
				temp = (temp < -.5f)? -.5f: temp;
				temp = (temp >  .5f)? .5f: temp;
				temp += (bad.getPosition().x  + temp2 < goodPed.getPosition().x + PedestrianEntity.SIZEX && bad.getPosition().x + temp2 > goodPed.getPosition().x)?
						PedestrianEntity.SIZEX * .7f :0;
				temp -= (goodPed.getPosition().x  < bad.getPosition().x + temp2+ BaddyPedestrianEntity.SIZEX && goodPed.getPosition().x  > bad.getPosition().x + temp2)?
						BaddyPedestrianEntity.SIZEX * .7f :0;
				temp2 += temp;

			}
			speedLimit = (isNexttoPeople)? ((isClosetoPeople)?.2f:.25f): .5f;
			temp2 = (temp2 < -speedLimit)? -speedLimit: temp2;
			temp2 = (temp2 >  speedLimit)? speedLimit: temp2;
			/*isNexttoPeople = false;
			for(PedestrianEntity ped: peds)
			{
				isNexttoPeople |= (temp2+bad.getPosition().x <ped.getPosition().x + PedestrianEntity.SizeX);
			}*/
			bad.getPosition().x += temp2;
			temp2 = 0;
			isNexttoPeople = false;
		}

	}

}
