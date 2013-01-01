package com.eshsprogramming.dash_or_smash.processor;

import com.badlogic.gdx.utils.Array;
import com.eshsprogramming.dash_or_smash.DashOrSmash;
import com.eshsprogramming.dash_or_smash.model.entity.pedestrian.PedestrianEntity;
import com.eshsprogramming.dash_or_smash.model.entity.pedestrian.baddy.BaddyPedestrianEntity;
import com.eshsprogramming.dash_or_smash.model.entity.pedestrian.baddy.BurglarBaddyPedestrianEntity;

/**
 * Controller for the baddies
 *
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
	 *
	 * @param baddies the array of baddies
	 * @param peds    the array of pedestrians that are going to influence the baddies
	 */
	public void updateBaddies(Array<BaddyPedestrianEntity> baddies, Array<PedestrianEntity> peds)
	{
		float temp = 0, temp2 = 0, speedLimit = .25f , runSpeed = .02f; //sets up some needed variables
		boolean isNexttoPeople = false, isClosetoPeople = false;;

		for(BaddyPedestrianEntity bad: baddies)
		{
			for(PedestrianEntity goodPed: peds)
			{
				isNexttoPeople |= (bad.getPosition().x  + temp2  + .5f + BurglarBaddyPedestrianEntity.SIZEX
						>  goodPed.getPosition().x &&
						bad.getPosition().x  + temp2  - .5f <  goodPed.getPosition().x +PedestrianEntity.SIZEX);
				//tests if within .5f of any pedestrian
				isClosetoPeople |= (bad.getPosition().x  + temp2  + .1f + BurglarBaddyPedestrianEntity.SIZEX
						>  goodPed.getPosition().x &&
						bad.getPosition().x  + temp2  - .1f <  goodPed.getPosition().x +PedestrianEntity.SIZEX);
				//tests if within .2 of any pedestrian
				temp = ((isNexttoPeople)?((isClosetoPeople)?.0005f:.003f):.01f) / (goodPed.getPosition().x -bad.getPosition().x);
				//basic movement code follows rational fcn scheme
				temp = (temp < -.3f)? -.3f: temp; //makes sure each temp is not larger than 5
				temp = (temp >  .3f)? .3f: temp;

				temp += (bad.getPosition().x  + temp2 < goodPed.getPosition().x + PedestrianEntity.SIZEX && bad.getPosition().x
						+ temp2 > goodPed.getPosition().x)? PedestrianEntity.SIZEX * .2f :0;
				temp -= (goodPed.getPosition().x  < bad.getPosition().x + temp2+ BaddyPedestrianEntity.SIZEX &&
						goodPed.getPosition().x  > bad.getPosition().x + temp2) ? BaddyPedestrianEntity.SIZEX * .2f :0;
				//does collision for push back
				temp2 += temp;
				//adds movement for the ped to total movement

			}
			speedLimit = (isNexttoPeople)? ((isClosetoPeople)?0:.3f): .4f; //sets speed limit
			temp2 = (temp2 < -speedLimit)? -speedLimit: temp2;  //makes speed limit obeyed
			temp2 = (temp2 >  speedLimit)? speedLimit: temp2;
			bad.getVelocity().x /= 1.3f; //friction
			bad.getVelocity().x += temp2; //adds temp to veloc
			bad.getPosition().x += bad.getVelocity().x;   //adds movement to the bad guy
			temp2 = 0;
			isNexttoPeople = false;     //resets stuff for next bad guy
			bad.specialEffect(isClosetoPeople);
		}

	}

}
