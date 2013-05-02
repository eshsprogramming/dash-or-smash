package com.eshsprogramming.dash_or_smash.processor;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.eshsprogramming.dash_or_smash.DashOrSmash;
import com.eshsprogramming.dash_or_smash.model.entity.pedestrian.PedestrianEntity;
import com.eshsprogramming.dash_or_smash.view.Renderer;

/**
 * Improved version of multi touch processor that can better deal with controlling pedestrians
 *
 * @author Benjamin Landers
 */
public class PedestrianController extends EntityController
{
    int[] controlling = null;
    boolean[] isControlling = null;

    /**
     * Creates a new MultiTouchProcessor instance. Also sets the max touches that it can deal with.
     *
     * @param maxTouches the max number of touches
     */
    public PedestrianController(DashOrSmash game, int maxTouches)
    {
        super(game, maxTouches);
        controlling = new int[maxTouches];
        isControlling = new boolean[maxTouches];
    }

    /**
     * updates the positions of the group of pedestrians
     *
     * @param pedestrians an array of pedestrians that are controllable
     */
    public void updatePedestrians(Array<PedestrianEntity> pedestrians)
    {
        Vector2 temp;
        PedestrianEntity pedestrian;

        for(int i = 0; i < getPositions().length; i++)
        {
            temp = getPositions()[i];

            if(temp.x == -5 || temp.y == -5)
            {
                continue;
            }

            for(int i2 = 0; i2 < pedestrians.size; i2++)
            {
                pedestrian = pedestrians.get(i2);

                if(controlling[i] == i2)
                {
                    if(temp.x - .5f < pedestrian.getPosition().x + PedestrianEntity.SIZEX && temp.x + .5f
                            > pedestrian.getPosition().x)
                    {
                        pedestrian.getPosition().x = temp.x - PedestrianEntity.SIZEX / 2;
                        isControlling[i] = true;
                    }
                }

                else
                {
                    if(!isControlling[i] && temp.x < pedestrian.getPosition().x + PedestrianEntity.SIZEX && temp.x
                            > pedestrian.getPosition().x)
                    {
                        pedestrian.getPosition().x = temp.x - PedestrianEntity.SIZEX / 2;
                        controlling[i] = i2;
                        isControlling[i] = true;
                    }
                }

                pedestrian.getPosition().x = (pedestrian.getPosition().x > 0) ? pedestrian.getPosition().x : 0;
                pedestrian.getPosition().x = (pedestrian.getPosition().x <
                                              Renderer.CAMERA_WIDTH - PedestrianEntity.SIZEX) ? pedestrian.getPosition().x :
                                             Renderer.CAMERA_WIDTH - PedestrianEntity.SIZEX;
            }

            isControlling[i] = false;
        }
    }
}
