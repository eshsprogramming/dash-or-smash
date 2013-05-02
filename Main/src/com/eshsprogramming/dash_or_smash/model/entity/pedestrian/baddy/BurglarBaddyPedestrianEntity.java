package com.eshsprogramming.dash_or_smash.model.entity.pedestrian.baddy;

import com.badlogic.gdx.math.Vector2;
import com.eshsprogramming.dash_or_smash.model.gui.Score;

/**
 * @author Zachary Latta
 */
public class BurglarBaddyPedestrianEntity extends BaddyPedestrianEntity
{
    /**
     * A REF to a score object
     */
    public Score score = null;
    /**
     * The chance of a burglar spawning each frame out of 10000.
     */
    public static final float SPAWN_CHANCE = 10f;
    /**
     * The cost per frame when a burgler is next to a ped.
     */
    public static final float BURGLE_POINTS = 1;

    /**
     * Creates a new burglar.
     *
     * @param position The burglar's starting position.
     */
    public BurglarBaddyPedestrianEntity(Vector2 position, Score score)
    {
        super(position, 250, 50);
        this.score = score;
    }
    public void rob()
    {
        score.baddyBurgle((int)  BURGLE_POINTS);
    }

    @Override
    public void specialEffect(boolean closeToPedestrian)
    {
        if(closeToPedestrian)
        {
            rob();
        }
    }
}
